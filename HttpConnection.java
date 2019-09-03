//Name: Li Sing
//ID: 54805981
//line 36: Extra Function 1: Open downloaded file directly
//line 74: Extra Function 2: Change file size to appropriate unit 
//line 103: Extra Funtion 3: Limit output to 2 decimal places.
//line 117: Extra Funtion 4: Alert can not connect to host/ invalid URL
package singli2;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author sjxy
 */
public class HttpConnection implements Runnable {

    String link;
    File out;
    MyDownTask task;
    boolean isPause = false;

    HttpConnection(String l, File o, MyDownTask m) {
        this.out = o;
        this.link = l;
        this.task = m;//Parent, MyDownTask reference
    }

    //called at MyDownTask when Pause Button clicked
    synchronized public void pauseDownload() {
        //Extra Function 1: Open file directly---------------------------------
        //Pause button change to Open functino only now
        if (task.getPause().getText().equals("Open")) {
            task.getPause().setSelected(false);
            try {
                Desktop.getDesktop().open(new File(out.getAbsolutePath()));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(task, "File Moved/Deleted/Not Exsist");
            }
        } else {//Pause/Resume Function
            if (task.getPause().isSelected()) {
                task.getPause().setText("Resume");
                isPause = true;
            } else {
                task.getPause().setText("Pause");
                isPause = false;
                notify();//resume thread download
            }
        }
    }

    @Override
    public void run() {

        try {
            task.getProgress().setStringPainted(true);
            task.getProgress().setString("Start Downloading...");//inforam download start
            URL url = new URL(link);
            URLConnection urlcon = (URLConnection) url.openConnection();//start connect URL
            double fileSize = (double) urlcon.getContentLengthLong();//get fileSize
            BufferedInputStream in = new BufferedInputStream(urlcon.getInputStream());
            FileOutputStream fout = new FileOutputStream(this.out);
            BufferedOutputStream bout = new BufferedOutputStream(fout, 1024);//1024byte
            byte[] buffer = new byte[1024];//1KB
            double download = 0.00;//data downloaded size
            int read = 0;//BufferedInputStream read count
            int percent;
            //Extra Funtion 2: Change file size unit-----------------------------------------------
            double divide = 1;
            String unit = "B";
            if (fileSize < 1024 * 1024) {
                unit = "KB";
                divide = 1024;
            } else if (fileSize < 1024 * 1024 * 1024) {
                unit = "MB";
                divide = 1024 * 1024;
            } else if (fileSize < 1024 * 1024 * 1024 * 1024) {
                unit = "GB";
                divide = 1024 * 1024 * 1024;
            }

            while ((read = in.read(buffer, 0, 1024)) >= 0) {//-1 when read done 
                try {
                    synchronized (this) {
                        while (isPause) {
                            wait();
                        }
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Pause fail");
                }

                bout.write(buffer, 0, read);
                download += read;
                percent = (int) ((download * 100) / fileSize);//progress bar
                //Extra Funtion 3: Limit output to 2 decimal places.------------------------------------------------
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                task.getProgress().setValue(percent);
                task.getProgress().setString(task.name + " | " + percent + "%" + " | "
                        + numberFormat.format(download / divide) + "/" + numberFormat.format(fileSize / divide) + unit);
                if (percent == 100) {
                    //task.getClear().setEnabled(true);
                    task.getClear().setEnabled(true);
                    task.getPause().setText("Open");
                }
            }
            bout.close();
            in.close();

        } catch (IOException ex) {//Extra Funtion 4: alert can not connect to host/ invalid URL
            task.removeBar();
            JOptionPane.showMessageDialog(task, "Can not connect to the host!\nPlease enter again");
        } catch (IllegalArgumentException ex) {//alert invalid URL 
            task.removeBar();
            JOptionPane.showMessageDialog(task, "Invalid URL format!\nPlease enter again");

        }

    }

}

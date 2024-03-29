
package singli;

import java.awt.Dimension;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author singli
 */
public class MyDownloadManager extends javax.swing.JFrame {

    public static int No_Bar = 0;
    public static boolean over = false;
    String storePlace = System.getProperty("user.dir");//getting user working directory

    /**
     * Creates new form NewJFrame
     */
    public MyDownloadManager() {

        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enter = new javax.swing.JPanel();
        URL = new javax.swing.JTextField();
        start = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        down = new javax.swing.JPanel();
        Setting = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File Download Manager (Li Sing 54805981)");

        enter.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter a URL to download / Enter the download directory and press Set Path"));

        URL.setText("https://");
        URL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                URLActionPerformed(evt);
            }
        });

        start.setText("Start");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enterLayout = new javax.swing.GroupLayout(enter);
        enter.setLayout(enterLayout);
        enterLayout.setHorizontalGroup(
            enterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(URL, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(start)
                .addGap(6, 6, 6))
        );
        enterLayout.setVerticalGroup(
            enterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(enterLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(enterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(URL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(start))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new java.awt.Dimension(576, 170));

        down.setBorder(new javax.swing.border.MatteBorder(null));
        down.setMaximumSize(new java.awt.Dimension(20000, 20000));
        down.setPreferredSize(new java.awt.Dimension(550, 0));
        down.setLayout(new javax.swing.BoxLayout(down, javax.swing.BoxLayout.Y_AXIS));
        scroll.setViewportView(down);

        Setting.setText("Set Path");
        Setting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Setting)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Setting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void URLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_URLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_URLActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        String link = URL.getText();
        //Please type the download folder path name here ^
        try {
            String fileName = SetFileName(link, storePlace);
            File out = new File(storePlace + "/" + fileName);
            MyDownTask downloads = new MyDownTask(down, scroll, link, out);
            downloads.addToJP(fileName);//add download progress bar and start downloading
            addHeight();//increse panel height to contain new progressbar if needed
        } catch (NullPointerException ex) {
//Extra Function 5: Alert wrong directory and reset to default--------------------
            JOptionPane.showMessageDialog(this, "Invalid directory location!\nPlease set the directory again"
                    + "\nPath reset to current wokting directory");
            storePlace = System.getProperty("user.dir");//set back to working directory
        }
        URL.setText("https://");//reset
        //https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png
    }//GEN-LAST:event_startActionPerformed
//Extra Function 6: Allow user to set own download directory
    private void SettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingActionPerformed
        storePlace = URL.getText();
        JOptionPane.showMessageDialog(this, "Directory path set as: " + URL.getText());
    }//GEN-LAST:event_SettingActionPerformed

    private String SetFileName(String link, String storePlace) {
        String name;
        //rename function-----------------
        int end = link.lastIndexOf('/');
        name = link.substring(end + 1, link.length());//get the download file name
        File folder = new File(storePlace);
        File[] fileList = folder.listFiles();
        Set<String> List = new HashSet<>();
        for (File f : fileList) {//get all the files name in the download directory
            if (f.isFile()) {
                List.add(f.getName());
            }
        }

        if (!List.contains(name)) {//no same file name
            return name;
        } else {
            int i = 1;
            String rename;
            String front = name.substring(0, name.lastIndexOf("."));//get file name
            String back = name.substring(name.lastIndexOf("."), name.length());//get file format ex: .pdf
            do {
                rename = front + "(" + i + ")" + back;//rename file name
                i++;
            } while (List.contains(rename));//check dulplicates
            return rename;
        }

    }

    public void addHeight() {//increase panel height
        No_Bar++;
        if (No_Bar > 4) {//deafult panel only able contain 4 progressbar
            over = true;  //count the accumulated height of all progress bar before over panel height
        }
        if (over) {
            Dimension a = new Dimension(down.getWidth(), down.getHeight() + 40);//add around 40 height
            down.setPreferredSize(a);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyDownloadManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyDownloadManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyDownloadManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyDownloadManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyDownloadManager().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Setting;
    private javax.swing.JTextField URL;
    private javax.swing.JPanel down;
    private javax.swing.JPanel enter;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables

}

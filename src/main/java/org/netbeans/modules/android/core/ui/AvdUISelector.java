/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.netbeans.modules.android.core.ui;

import com.android.sdklib.AndroidVersion;
import com.android.sdklib.internal.avd.AvdInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.openide.util.NbBundle;

/**
 *
 * @author radim
 */
public class AvdUISelector extends javax.swing.JPanel {
  private static final Logger LOG = Logger.getLogger(AvdUISelector.class.getName());

  private AvdInfo[] avdInfos;

  public AvdUISelector(AvdInfo [] infos) {
    this();
    setAvdInfos(infos);
  }

  /** Creates new form AvdUISelector */
  public AvdUISelector() {
      initComponents();
  }

  public JTable getTable() {
    return avdsTable;
  }

  public DefaultTableModel getTableModel() {
    return (DefaultTableModel)avdsTable.getModel();
  }

  public void setAvdByName(String name) {
    if (name == null) {
      avdsTable.getSelectionModel().clearSelection();
    } else {
      for (int i = 0; i < avdInfos.length; i++) {
        AvdInfo info = avdInfos[i];
        if (name.equals(info.getName())) {
          avdsTable.getSelectionModel().setSelectionInterval(i, i);
          return;
        }
      }
    }
  }

  public AvdInfo getAvdInfo() {
    int row = avdsTable.getSelectedRow();
    if (row >= 0) {
      return avdInfos[row];
    }
    return null;
  }

  public String getAvdInfoName() {
    AvdInfo info = getAvdInfo();
    return info != null ? info.getName() : null;
  }

  public final void setAvdInfos(AvdInfo [] infos) {
    avdInfos = infos;
    DefaultTableModel tableModel = getTableModel();
    tableModel.setRowCount(0);
    for (AvdInfo info : infos) {
        AndroidVersion target = info.getAndroidVersion();
      if (target != null) {
        tableModel.addRow(new Object[] {
            info.getName(), target.getApiLevel(), target.getCodename(), target.getFeatureLevel()
        });
      } else {
        LOG.log(Level.INFO, "Not valid AvdInfo {0}", info);
      }
    }
    if (infos.length > 0) {
      avdsTable.setRowSelectionInterval(0, 0); // pre-select 1st row
    }
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        avdsTable = new JTable();
        avdDescLbl = new JLabel();

        avdsTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AVD Name", "Target Name", "Platform", "API Level"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        avdsTable.setPreferredSize(null);
        avdsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(avdsTable);
        avdsTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        avdsTable.getColumnModel().getColumn(0).setHeaderValue(NbBundle.getMessage(AvdUISelector.class, "AvdUISelector.avdsTable.columnModel.title0"));         avdsTable.getColumnModel().getColumn(1).setHeaderValue(NbBundle.getMessage(AvdUISelector.class, "AvdUISelector.avdsTable.columnModel.title1"));         avdsTable.getColumnModel().getColumn(2).setHeaderValue(NbBundle.getMessage(AvdUISelector.class, "AvdUISelector.avdsTable.columnModel.title2"));         avdsTable.getColumnModel().getColumn(3).setHeaderValue(NbBundle.getMessage(AvdUISelector.class, "AvdUISelector.avdsTable.columnModel.title3")); 
        avdDescLbl.setText(NbBundle.getMessage(AvdUISelector.class, "AvdUISelector.avdDescLbl.text")); 
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(avdDescLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(avdDescLbl)
                .addContainerGap())
        );

        avdDescLbl.getAccessibleContext().setAccessibleName(NbBundle.getMessage(AvdUISelector.class, "AvdUISelector.jLabel1.AccessibleContext.accessibleName"));     }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel avdDescLbl;
    private JTable avdsTable;
    private JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}

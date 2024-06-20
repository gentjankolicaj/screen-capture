package io.screencapture.server.gui;

import io.screencapture.server.config.ServerConfig;
import java.awt.GridLayout;
import java.net.Socket;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ServerGui extends JFrame {

  private GridLayout mainLayout;
  private JPanel mainPanel;
  private JList<DefaultListModel<ClientSocketModel>> clients;
  private DefaultListModel<ClientSocketModel> listModel;

  public ServerGui() {
    super();
    panelInit();
  }

  private void panelInit() {
    mainLayout = new GridLayout(1, 2);
    mainPanel = new JPanel();

    listModel = new DefaultListModel<>();

    clients = new JList(listModel);
    clients.setSelectionModel(new DefaultListSelectionModel());

    //Add panel components
    mainPanel.add(new JScrollPane(clients));

    //Set panel layout
    mainPanel.setLayout(mainLayout);

    //Add main panel to frame
    this.add(mainPanel);

    initFrame();
  }


  private void initFrame() {
    setSize(ServerConfig.SCENE_WIDTH, ServerConfig.SCENE_HEIGHT);
    setTitle(ServerConfig.GUI_TITLE);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }


  public void updateSocketClientGui(List<Socket> socketClients) {
    if (socketClients != null && socketClients.size() != 0) {
      for (Socket client : socketClients) {
        if (!listModel.contains(new ClientSocketModel(client))) {
          listModel.addElement(new ClientSocketModel(client));
        }
      }
    }
    this.repaint();
  }
}

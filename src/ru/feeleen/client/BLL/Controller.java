package ru.feeleen.client.BLL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.SocketException;

public class Controller {
    @FXML
    private Button buttonConnect;
    @FXML
    private Button buttonSend;

    @FXML
    TextField textFieldMessage;

    @FXML
    TextArea textAreaOutIn;

    @FXML
    Pane connectPane;

    @FXML
    public void initialize() {
        setDisenableConnectParametr();
    }

    private boolean connectEnable = false;

    private Client client = new Client();

    public void connect(ActionEvent actionEvent) {
        try {
            if (!connectEnable) {
                client.clientGo();
                setEnableConnectParametrs();
            } else {
                setDisenableConnectParametr();
                client.disconnect();
            }

        } catch (IOException e) {
            setDisenableConnectParametr();
            System.err.println("Connect error");
            e.printStackTrace();
        }
    }

    private void setDisenableConnectParametr() {
        buttonSend.setDisable(true);
        textFieldMessage.setDisable(true);
        connectEnable = false;
        connectPane.setStyle("-fx-background-color: rgb(198, 31, 31)");
        buttonConnect.setStyle("-fx-background-color: rgba(195,248,0,0.85)");
        buttonConnect.setText("Connect");
    }

    private void setEnableConnectParametrs() {
        connectEnable = true;
        buttonSend.setDisable(false);
        textFieldMessage.setDisable(false);
        connectPane.setStyle("-fx-background-color: rgb(185,248,48)");
        buttonConnect.setStyle("-fx-background-color: rgba(248,36,26,0.50)");
        buttonConnect.setText("disconnect");
    }

    public void sendMessenge(ActionEvent actionEvent) {
        try {
            if (!client.isClosed()) {
                if (textFieldMessage.getText() != null || !textFieldMessage.getText().equals("")) {
                    try {
                        client.MessengeSend(textFieldMessage.getText());
                        textAreaOutIn.appendText("message: " + textFieldMessage.getText() + "\n");
                        textFieldMessage.setText("");
                        textAreaOutIn.appendText("Answer from server: " + client.ReportOfServer() + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                setDisenableConnectParametr();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
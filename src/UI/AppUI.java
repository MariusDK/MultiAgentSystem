package UI;

import Auction.BidderAgent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppUI implements Initializable{
    @FXML private ListView id_list;
    @FXML private Button bidder_id;
    @FXML private Button id_start;
    @FXML private Button id_auctioner;

    public AppUI()
    {}

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        id_list.setFixedCellSize(48);

    }
    private void loadWindow(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AppUI.fxml"));
            AnchorPane root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadWindowAuctioneer(String name)
    {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Add_Auctioneer.fxml"));
            AnchorPane root = fxmlLoader.load();
            Add_Auctioneer add_auctioneer = fxmlLoader.<Add_Auctioneer>getController();
            BidderAgent bidderAgent;//aici adaugam in lista

            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void AuctoneerAdd() {
        id_auctioner.setOnAction(event -> {
                loadWindowAuctioneer("Auctioneer");
        });
    }
    public void loadWindowBidder(String name)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Add_bidder.fxml"));
            AnchorPane root = fxmlLoader.load();
            Add_bidder add_bidder = fxmlLoader.<Add_bidder>getController();
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void BidderAdd() {
        bidder_id.setOnAction(event -> {
            loadWindowBidder("Bidder");
        });
    }


}

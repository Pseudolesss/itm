package application;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

public class Main extends Application{
	
	private String SQLConnector = "jdbc:mysql://localhost/gib?allowMultiQueries=true";
	private String SQLUser = "root";
	private String SQLPassword = "";

    @Override
    public void start(Stage primaryStage) {
		
        primaryStage.setTitle("GIB information manager");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        ScrollPane sp = new ScrollPane();
        sp.setContent(gridPane);
        
        Scene scene = new Scene(sp, 300, 275);

        // CREATE CUSTOMER
        Text sceneTitleCustomer = new Text("Create new customer");
        sceneTitleCustomer.setFont(Font.font("Arial", FontWeight.NORMAL,18));
        gridPane.add(sceneTitleCustomer, 0, 0, 2, 1);
        // Forename
        Label labelForeNameCustomer = new Label("Forename:");
        gridPane.add(labelForeNameCustomer, 0, 1);
        final TextField foreNameCustomerField = new TextField();
        gridPane.add(foreNameCustomerField, 1, 1);
        // Last name
        Label labelLastNameCustomer = new Label("Last Name:");
        gridPane.add(labelLastNameCustomer,0,2);
        final TextField lastNameCustomerField = new TextField();
        gridPane.add(lastNameCustomerField, 1, 2);
        // Address
        Label labelAddressCustomer = new Label("Address:");
        gridPane.add(labelAddressCustomer, 0, 3);
        final TextField addressCustomerField = new TextField();
        gridPane.add(addressCustomerField, 1, 3);
        // Postal Code
        Label labelPostalCodeCustomer = new Label("Postal Code:");
        gridPane.add(labelPostalCodeCustomer, 0, 4);
        final TextField postalCodeCustomerField = new TextField();
        gridPane.add(postalCodeCustomerField, 1, 4);
        // Email
        Label labelEmailCustomer = new Label("Email:");
        gridPane.add(labelEmailCustomer, 0, 5);
        final TextField emailCustomerField = new TextField();
        gridPane.add(emailCustomerField, 1, 5);
        // Credit Limit (Integer)
        Label labelCreditLimitCustomer = new Label("Credit Limit:");
        gridPane.add(labelCreditLimitCustomer, 0, 6);
        final Spinner<Integer> creditLimitCustomerField = new Spinner<>(0, 10000, 0);
        creditLimitCustomerField.setRepeatDelay(new Duration(10));
        gridPane.add(creditLimitCustomerField, 1, 6);
        // Gender
        final ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleButton = new RadioButton ("M");
        RadioButton femaleButton = new RadioButton ("F");
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        gridPane.add(new HBox(maleButton, femaleButton), 1, 7);
        genderGroup.selectToggle(maleButton);
        Label labelGenderCustomer = new Label("Gender:");
        gridPane.add(labelGenderCustomer, 0, 7);
        // Address
        final TextField addressGenderField = new TextField();
        //paneCustomer.add(addressGenderField, 1, 7);
        // Date Of Birth (DateTime)
        Label labelDateOfBirthCustomer = new Label("Date of Birth:");
        gridPane.add(labelDateOfBirthCustomer, 0, 8);
        final DatePicker dateOfBirthGenderField = new DatePicker();
        gridPane.add(dateOfBirthGenderField, 1, 8);
        
        Button validateButtonCustomer = new Button("Create");        
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(validateButtonCustomer);
        gridPane.add(hbox, 1, 9); 

        final Text insertMessageCustomer = new Text();
        gridPane.add(insertMessageCustomer, 1, 10);
        
        
        // CREATE PRODUCT
        Text sceneTitleProduct = new Text("Create new product");
        sceneTitleProduct.setFont(Font.font("Arial", FontWeight.NORMAL,18));
        gridPane.add(sceneTitleProduct, 2, 0, 2, 1);
        // Name
        Label labelNameProduct = new Label("Name:");
        gridPane.add(labelNameProduct, 2, 1);
        final TextField nameProductField = new TextField();
        gridPane.add(nameProductField, 3, 1);
        // Code
        Label labelCodeProduct = new Label("Code:");
        gridPane.add(labelCodeProduct,2,2);
        final TextField codeProductField = new TextField();
        gridPane.add(codeProductField, 3, 2);
        // Selling Price
        Label labelSellingPriceProduct = new Label("Selling Price:");
        gridPane.add(labelSellingPriceProduct, 2, 3);
        final Spinner<Double> sellingPriceProductField = new Spinner<>(0, 10000, 0.00, 0.01);
        sellingPriceProductField.setRepeatDelay(new Duration(1));
        gridPane.add(sellingPriceProductField, 3, 3);
        // Information
        Label labelInformationProduct = new Label("Information:");
        gridPane.add(labelInformationProduct, 2, 4);
        final TextField informationProductField = new TextField();
        gridPane.add(informationProductField, 3, 4);
        
        Button validateButtonProduct = new Button("Create");        
        HBox hboxButtonProduct = new HBox(10);
        hboxButtonProduct.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButtonProduct.getChildren().add(validateButtonProduct);
        gridPane.add(hboxButtonProduct, 3, 5);

        final Text insertMessageProduct = new Text();
        gridPane.add(insertMessageProduct, 3, 6);
        
        
        // CREATE ORDER
        Text sceneTitleOrder = new Text("Create new order");
        sceneTitleOrder.setFont(Font.font("Arial", FontWeight.NORMAL,18));
        gridPane.add(sceneTitleOrder, 4, 0, 2, 1);
        // Forename
        Label labelForeNameOrder = new Label("Forename:");
        gridPane.add(labelForeNameOrder, 4, 1);
        final TextField foreNameOrderField = new TextField();
        gridPane.add(foreNameOrderField, 5, 1);
        // Last name
        Label labelLastNameOrder = new Label("Last Name:");
        gridPane.add(labelLastNameOrder,4,2);
        final TextField lastNameOrderField = new TextField();
        gridPane.add(lastNameOrderField, 5, 2);
        
        // List (to store the desired quantity to order for each product available in DB)
        List<Pair<Product, Spinner<Integer>>> listOrdering = new ArrayList<Pair<Product, Spinner<Integer>>>();
        
        Button orderingButton = new Button("Create");        
        HBox hboxButtonOrdering = new HBox(10);
        hboxButtonOrdering.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButtonOrdering.getChildren().add(orderingButton);
        //gridPane.add(hboxButtonOrdering, 5, 3);

        final Text orderingMessage = new Text();
        //gridPane.add(orderingMessage, 5, 4);
        
        updateNewOrderForm(gridPane, listOrdering, hboxButtonOrdering, orderingMessage, 4, 3);
        
        
        // DISPLAY PRODUCT SEARCH
        Text sceneTitleDisplayProduct = new Text("Search a product");
        sceneTitleDisplayProduct.setFont(Font.font("Arial", FontWeight.NORMAL,18));
        gridPane.add(sceneTitleDisplayProduct, 6, 0, 2, 1);
        // Name
        Label labelNameDisplayProduct = new Label("Name:");
        gridPane.add(labelNameDisplayProduct, 6, 1);
        final TextField nameDisplayProductField = new TextField();
        gridPane.add(nameDisplayProductField, 7, 1);
        // Ordering by Name (ascending), Ordering by Price ( descending)
        final ToggleGroup orderingByGroup = new ToggleGroup();
        RadioButton orderingByNameButton = new RadioButton ("By Name");
        RadioButton orderingByPriceButton = new RadioButton ("By Price");
        orderingByNameButton.setToggleGroup(orderingByGroup);
        orderingByPriceButton.setToggleGroup(orderingByGroup);
        gridPane.add(new HBox(orderingByNameButton, orderingByPriceButton), 7, 2);
        orderingByGroup.selectToggle(orderingByNameButton);
        Label labelOrdering = new Label("Ordering:");
        gridPane.add(labelOrdering, 6, 2);
        
        Button validateButtonDisplayProduct = new Button("Search");        
        HBox hboxButtonDisplayProduct = new HBox(10);
        hboxButtonDisplayProduct.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButtonDisplayProduct.getChildren().add(validateButtonDisplayProduct);
        gridPane.add(hboxButtonDisplayProduct, 7, 3);
        
        TableView productsTable = new TableView();
        productsTable.setPlaceholder(new Label("No rows to display"));
        gridPane.add(productsTable, 6, 4, 2, 9);
        
        TableColumn<Product, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setSortable(false);
        TableColumn<Product, String> code = new TableColumn<>("Code");
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        code.setSortable(false);
        TableColumn<Product, String> sellingPrice = new TableColumn<>("Selling Price");
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingPrice.setSortable(false);
        TableColumn<Product, String> information = new TableColumn<>("Information");
        information.setCellValueFactory(new PropertyValueFactory<>("information"));
        information.setSortable(false);
        TableColumn<Product, String> stockLevel = new TableColumn<>("Stock Level");
        stockLevel.setCellValueFactory(new PropertyValueFactory<>("stockLevel"));
        stockLevel.setSortable(false);

        productsTable.getColumns().add(name);
        productsTable.getColumns().add(code);
        productsTable.getColumns().add(sellingPrice);
        productsTable.getColumns().add(information);
        productsTable.getColumns().add(stockLevel);
        

        // UPDATE INVENTORY
        Text sceneTitleInventory = new Text("Update inventory level");
        sceneTitleInventory.setFont(Font.font("Arial", FontWeight.NORMAL,18));
        gridPane.add(sceneTitleInventory, 8, 0, 2, 1);
        // Code of product
        Label labelCodeInventory = new Label("Product code:");
        gridPane.add(labelCodeInventory, 8, 1);
        final TextField codeInventoryField = new TextField();
        gridPane.add(codeInventoryField, 9, 1);
     	// New inventory level (Integer)
        Label labelInventoryLevel = new Label("New inventory level:");
        gridPane.add(labelInventoryLevel, 8, 2);
        final Spinner<Integer> inventoryLevelField = new Spinner<>(0, 10000, 0);
        inventoryLevelField.setRepeatDelay(new Duration(10));
        gridPane.add(inventoryLevelField, 9, 2);
        
        Button validateInventory = new Button("Update");        
        HBox hboxInventory = new HBox(10);
        hboxInventory.setAlignment(Pos.BOTTOM_RIGHT);
        hboxInventory.getChildren().add(validateInventory);
        gridPane.add(hboxInventory, 9, 3);
        
        final Text insertMessageInventory = new Text();
        gridPane.add(insertMessageInventory, 9, 4);


        validateButtonProduct.setOnAction(new EventHandler<ActionEvent>() {

           
            public void handle(ActionEvent t) {
            	Connection connection;
            	String productName = nameProductField.getText();
            	String productCode = codeProductField.getText();
            	Double productSellingPrice = sellingPriceProductField.getValue();
                String productInformation = informationProductField.getText();
                
        		try {
        			connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);

        		} catch (SQLException e) {
        			System.out.println("Connection Failed! Check output console");
        			insertMessageProduct.setText("An error occurs: Check output console");
        			e.printStackTrace();
        			return;
        		}

        		if(connection !=null) {
        			try {
        				// Check fields
        				String parsingCheck = checkProductForm(productName, productCode);
        				if (parsingCheck != "")
        					insertMessageProduct.setText("The form is not valid: \n" + parsingCheck);
        				
        				else {
        					// Check if product already exists
        					Statement stmt =connection.createStatement();
        					ResultSet rs;
        					rs = stmt.executeQuery("SELECT COUNT(*) FROM products WHERE `code` LIKE '"+ productCode + "'");
        					rs.next();
        					
        					// If the product already exists 
        					if (rs.getInt(1) != 0) {
        						insertMessageProduct.setText("The following product already exists: " + productName + " " + productCode);
        					}
        					
        					// else add the product in the database
        					else {
        						// Insert new product in Product table and insert new entry in Inventory at the same time (no inventory)
        						PreparedStatement insertProduct = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?); INSERT INTO inventory VALUES (?, ?);");
		        				insertProduct.setString(1, productName);
		        				insertProduct.setString(2, productCode);
		        				insertProduct.setDouble(3, productSellingPrice);
		        				insertProduct.setString(4, productInformation);
		        				insertProduct.setString(5, productCode);
		        				insertProduct.setInt(6, 0);
		        				insertProduct.executeUpdate();
		        				insertMessageProduct.setText("Product successfully created");
		        				// Refresh Ordering Form 
		        				updateNewOrderForm(gridPane, listOrdering, hboxButtonOrdering, orderingMessage, 4, 3);
		        				// Refresh TableView of products
		        				updateProductDisplay(productsTable, connection, ((RadioButton) orderingByGroup.getSelectedToggle()).getText(), nameDisplayProductField.getText());
        					}
        					
        					
        				}
        					
        				
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        				insertMessageProduct.setText("An error occurs: Check output console");
        			}
                informationProductField.setText("");
                codeProductField.setText("");
                nameProductField.setText("");
                sellingPriceProductField.getValueFactory().setValue(.0);
                return;
            }
            }
            });
        
        
        validateButtonDisplayProduct.setOnAction(new EventHandler<ActionEvent>() {

            
            public void handle(ActionEvent t) {
            	Connection connection;
                
                String name = nameDisplayProductField.getText();
                String ordering = ((RadioButton) orderingByGroup.getSelectedToggle()).getText();
                
        		try {
        			connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);

        		} catch (SQLException e) {
        			System.out.println("Connection Failed! Check output console");
        			insertMessageProduct.setText("An error occurs: Check output console");
        			e.printStackTrace();
        			return;
        		}

        		if(connection !=null) {
        			try {

        				updateProductDisplay(productsTable, connection, ordering, name);
        				
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        				insertMessageProduct.setText("An error occurs: Check output console");
        			}
                return;
            }
            }
            });
        
        validateButtonCustomer.setOnAction(new EventHandler<ActionEvent>() {

            
            public void handle(ActionEvent t) {
            	Connection connection;
            	String foreName = foreNameCustomerField.getText();
            	String lastName = lastNameCustomerField.getText();
            	String address = addressCustomerField.getText();
            	String postalCode = postalCodeCustomerField.getText();
            	String email = emailCustomerField.getText();
            	int creditLimit = creditLimitCustomerField.getValue();
            	String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            	LocalDate dateOfBirth = dateOfBirthGenderField.getValue();
            	
            	// Check validity of fields from form
            	String parsingCheck = checkClientForm(foreName, lastName, address, postalCode,
        	    		email, creditLimit, gender, dateOfBirth);
            	if (parsingCheck != "") {
            		insertMessageCustomer.setText("The form is not valid: \n" + parsingCheck);
            		return;
            	}
                
        		try {
        			connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);

        		} catch (SQLException e) {
        			System.out.println("Connection Failed! Check output console");
        			insertMessageCustomer.setText("An error occurs: Check output console");
        			e.printStackTrace();
        			return;
        		}

        		if(connection !=null) {
        			try {
    					// Check if customer already exists
    					Statement stmt =connection.createStatement();
    					ResultSet rs;
    					rs = stmt.executeQuery("SELECT COUNT(*) FROM `customers` WHERE `fore_name` LIKE '" + foreName + "' AND `last_name` LIKE '"+ lastName +"' ");
    					rs.next();
    					
    					// If the customer already exists 
    					if (rs.getInt(1) != 0) {
    						
    						insertMessageCustomer.setText("The following customer already exists: " + foreName + " " + lastName);
    					}
    					
    					// else add the product in the database
    					else {
    						PreparedStatement pstmt =
    								connection.prepareStatement("INSERT INTO `customers`"
    										+ "(`fore_name`, `last_name`, `address`, `postal_code`, `email`, `credit_limit`, `gender`, `date_of_birth`)"
    										+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	        				pstmt.setString(1, foreName);
	        				pstmt.setString(2, lastName);
	        				pstmt.setString(3, address);
	        				pstmt.setString(4, postalCode);
	        				pstmt.setString(5, email);
	        				pstmt.setInt(6, creditLimit);
	        				pstmt.setString(7, gender);
	        				pstmt.setDate(8, Date.valueOf(dateOfBirth));
	        				pstmt.executeUpdate();
	        				insertMessageCustomer.setText("Customer successfully created");
    					}
        				
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        				insertMessageCustomer.setText("An error occurs: Check output console");
        			}
        			
        			foreNameCustomerField.setText("");
        			lastNameCustomerField.setText("");
        			addressCustomerField.setText("");
        			postalCodeCustomerField.setText("");
        			emailCustomerField.setText("");
        			creditLimitCustomerField.getValueFactory().setValue(0);
        			orderingByGroup.selectToggle(orderingByNameButton);
        			//dateOfBirthGenderField
        			return;
            }
            }
            });
        
        
        	orderingButton.setOnAction(new EventHandler<ActionEvent>() {
        		        		
            
	            public void handle(ActionEvent t) {
	            	Connection connection;
	            	
	            	String foreName = foreNameOrderField.getText();
	        		String lastName = lastNameOrderField.getText();
	        		
	        		
	        		Date now = Date.valueOf(LocalDate.now());
	        		
    				double totalSellingPrice = 0;
            		
            		for(Pair<Product, Spinner<Integer>> p : listOrdering)
        				totalSellingPrice += p.getKey().getSellingPrice() * p.getValue().getValue();
            		
	        		try {
	        			connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);
	
	        		} catch (SQLException e) {
	        			System.out.println("Connection Failed! Check output console");
	        			orderingMessage.setText("An error occurs: Check output console");
	        			e.printStackTrace();
	        			return;
	        		}
	
	        		if(connection != null) {
	        			try {
	        				
	        				// Check if condition are matched for order processing (from project statement)
	                    	String checkOrdering = checkOrdering(listOrdering, foreName, lastName);
	                    	if (checkOrdering != "") {
	                    		orderingMessage.setText("The form is not valid: \n" + checkOrdering);
	                    		return;
	                    	}
	        				
	        				
	        				
	                		// Create order in database and decrement client credit
	        				PreparedStatement pstmt =
									connection.prepareStatement("INSERT INTO `orders`"
											+ "(`id`, `fore_name`, `last_name`, `transaction_date`, `total_price`)"
											+ "VALUES (NULL, ?, ?, ?, ?); "
											+ "UPDATE `customers` SET `credit_limit` = credit_limit - ? WHERE `customers`.`fore_name` = ? AND `customers`.`last_name` = ?; ");
	        				pstmt.setString(1, foreName);
	        				pstmt.setString(2, lastName);
	        				pstmt.setDate(3, now);
	        				pstmt.setDouble(4, totalSellingPrice);
	        				pstmt.setDouble(5, totalSellingPrice);
	        				pstmt.setString(6, foreName);
	        				pstmt.setString(7, lastName);
	        				pstmt.executeUpdate();
	        				
	        				
	        				// Add order products with sequentially operations and update corresponding stock level
	        				String query = "SELECT MAX(id) FROM orders INTO @lastId;";
	        				for(Pair<Product, Spinner<Integer>> p : listOrdering) {
	        					query += "INSERT INTO `order_products` (`order_id`, `product_code`, `quantity`) VALUES (@lastId, '" + p.getKey().getCode() + "', " + p.getValue().getValue() + ");";
	        					query += "UPDATE `inventory` SET `stock_level` = stock_level - " + p.getValue().getValue() + " WHERE `inventory`.`product_code` = '" + p.getKey().getCode() + "'; ";
	        				}
	        				
	        				pstmt = connection.prepareStatement(query);
	        				pstmt.execute();
	        				
	        				// Refresh TableView of products
	        				updateProductDisplay(productsTable, connection, ((RadioButton) orderingByGroup.getSelectedToggle()).getText(), nameDisplayProductField.getText());
	        				
	        				orderingMessage.setText("Order created successfully");
	        			}
	        			catch(Exception e) {
	        				e.printStackTrace();
	        				orderingMessage.setText("An error occurs: Check output console");
	        			}
	                return;
	            }
	            }
            	});
        
        
        validateInventory.setOnAction(new EventHandler<ActionEvent>() {

            
            public void handle(ActionEvent t) {
            	Connection connection;
            	
            	String code = codeInventoryField.getText();
            	int inventoryLevel = inventoryLevelField.getValue();
                
        		try {
        			connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);

        		} catch (SQLException e) {
        			System.out.println("Connection Failed! Check output console");
        			insertMessageInventory.setText("An error occurs: Check output console");
        			e.printStackTrace();
        			return;
        		}

        		if(connection != null) {
        			try {
        				
        				PreparedStatement pstmt = connection.prepareStatement("UPDATE `inventory` SET `stock_level` = '" + inventoryLevel + "' WHERE `inventory`.`product_code` = '" + code + "' ");
        				pstmt.executeUpdate();
        				
        				// Refresh TableView of products
        				updateProductDisplay(productsTable, connection, ((RadioButton) orderingByGroup.getSelectedToggle()).getText(), nameDisplayProductField.getText());
        				
        				insertMessageInventory.setText("Inventory updated successfully");
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        				insertMessageInventory.setText("An error occurs: Check output console");
        			}
                codeInventoryField.setText("");
                inventoryLevelField.getValueFactory().setValue(0);
                return;
            }
            }
            });
        
        

        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	    public static void main(String [] args){ 
	        launch(args); 
	     }
	    
	    // No real need to check the field by parsing
	    private static String checkProductForm(String name, String code) {
	    	String errorMessage = "";
	    	
	    	if(name == "" || code == "")
	    		errorMessage += "A name and a code should be given\n";
	    	
	    	return errorMessage;
	    }
	    
	    // Check Customer form fields
	    private static String checkClientForm(String foreName, String lastName, String address, String postalCode,
	    		String email, int creditLimit, String gender, LocalDate dateOfBirth) {
	    	
	    	String errorMessage = "";
	    	
	    	if (foreName == null || lastName == null || address == null || postalCode == null ||
    	    		email == null || gender == null || dateOfBirth == null)
	    		return "Each field of the form shoud be fullfiled";
	    	
	    	if (!isValidEmailAddress(email))
	    		errorMessage += "The given address \"" + email +"\" is not a valid email address \n";
	    	
	    	if(creditLimit < 0)
	    		errorMessage += "The credit limit should be a positive integer\n";
	    	
	    	if(dateOfBirth.isAfter(LocalDate.now()))
	    		errorMessage += "The customer is from a dystopic future!\n";
	    	
	    	return errorMessage;
	    }
	    
	    // Check through regular expression if the given string correspond to a mail address
	    private static boolean isValidEmailAddress(String email) {
	           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	           java.util.regex.Matcher m = p.matcher(email);
	           return m.matches();
	    }
	    
	    // Function to update product display
	    private static void updateProductDisplay(TableView productsTable, Connection connection, String ordering, String name) throws Exception {
	    	// Clear current TableView
			productsTable.getItems().clear();
			
			Statement stmt =connection.createStatement();
			ResultSet rs;
			// The matching is done in a case insensitive scenario
			// Also proceeding to an inner join operation to recover stock level
			String query = "SELECT * FROM `products` INNER JOIN `inventory` ON `products`.`code` = `inventory`.`product_code`"
					+ " WHERE `name` COLLATE LATIN1_GENERAL_CI  LIKE '%" + name + "%' ";
			
			if (ordering == "By Name")
				query += "ORDER BY `name` ASC";
			else
				query += "ORDER BY `selling_price` DESC";
			
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				productsTable.getItems().add(
						new Product(
								rs.getString("name"),
								rs.getString("code"),
								rs.getFloat("selling_price"),
								rs.getInt("stock_level"),
								rs.getString("information")));
			}
			
			return;
	    }
	    
	    private void removeNodeByRowColumnIndex(final int row,final int column,GridPane gridPane) {

	    	ObservableList<Node> childrens = gridPane.getChildren();
	    	for(Node node : childrens) {
	    	    if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
	    	        //ImageView imageView=new ImageView(node); // use what you want to remove
	    	        gridPane.getChildren().remove(node);
	    	        break;
	    	    }
	    	  } 
	    	   }
	    
	    // Function to update the sell form to buy products
	    private void updateNewOrderForm(GridPane gridPane, List<Pair<Product, Spinner<Integer>>> form, HBox hBox, Text orderingMessage, int gridColumn, int gridRow) {
	    	
	    	Connection connection;
	    	
	    	// Remove child from previous form
	    	for (int i = 0; i < form.size() + 2; i++) {
	    		removeNodeByRowColumnIndex(gridRow + i, gridColumn, gridPane);
	    		removeNodeByRowColumnIndex(gridRow + i, gridColumn + 1, gridPane);
	    	}
	    	
	    	form.clear();
        	
            
    		try {
    			connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);

    		} catch (SQLException e) {
    			System.out.println("Connection Failed! Check output console");
    			orderingMessage.setText("An error occurs: Check output console");
    			e.printStackTrace();
    			return;
    		}

    		if(connection != null) {
    			try {
    				
    				Statement stmt =connection.createStatement();
    				ResultSet rs;
    				// The matching is done in a case insensitive scenario
    				// Also proceeding to an inner join operation to recover stock level
    				String query = "SELECT * FROM `products` INNER JOIN `inventory` ON `products`.`code` = `inventory`.`product_code`";
    				
    				rs = stmt.executeQuery(query);
    				while(rs.next()) {
    					form.add(new Pair(
    							new Product(
    									rs.getString("name"),
    									rs.getString("code"),
    									rs.getFloat("selling_price"),
    									rs.getInt("stock_level"),
    									rs.getString("information")), new Spinner<Integer>(0, 10000, 0)));
    				}
    				
    				
    			}
    			catch(Exception e) {
    				e.printStackTrace();
    				orderingMessage.setText("An error occurs: Check output console");
    			}
	            //codeInventoryField.setText("");
	            //inventoryLevelField.getValueFactory().setValue(0);
	    			
	    		// Add in hierarchy tree integer field for the buying formula
	            for (int i = 0; i < form.size(); i++) {
	            	Product prod = form.get(i).getKey();
	            	
	            	gridPane.add(new Text(prod.getName() + '\n' + "Price: " + prod.getSellingPrice() + "€"), gridColumn, gridRow + i);
	            	gridPane.add(form.get(i).getValue(), gridColumn + 1, gridRow + i);
	            }
	            
	            // Add at last button and message in right place
	            gridPane.add(hBox, gridColumn + 1, gridRow + form.size());
	            gridPane.add(orderingMessage, gridColumn + 1, gridRow + form.size() + 1);
	            
    		}
	    	
	    }
	    
	    private String checkOrdering(List<Pair<Product, Spinner<Integer>>> form, String foreName, String lastName) throws Exception {
	    	String errorMessage = "";
	    	
	    	Connection connection = DriverManager.getConnection(SQLConnector, SQLUser, SQLPassword);
	    	
	    	// Check if client exists
	    	
			Statement stmt =connection.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT COUNT(*) FROM `customers` WHERE `fore_name` LIKE '" + foreName + "' AND `last_name` LIKE '"+ lastName +"' ");
			rs.next();
			
			// If the customer exists 
			if (rs.getInt(1) == 0) {
				errorMessage += "The following customer does not exists: \"" + foreName + " " + lastName + "\"\n";
				return errorMessage;
			}
			
			rs = stmt.executeQuery("SELECT * FROM `customers` WHERE `fore_name` LIKE '" + foreName + "' AND `last_name` LIKE '"+ lastName +"' ");
			rs.next();
			int creditLimit = rs.getInt("credit_limit");
			double totalSellingPrice = 0;
			
			for(Pair<Product, Spinner<Integer>> product : form){
				// Increment Selling Price * Quantity 
				totalSellingPrice += product.getKey().getSellingPrice() * product.getValue().getValue();
			}
			
			if (creditLimit < (int) totalSellingPrice)
				errorMessage += "Credit limit insufficient (" + creditLimit + ") => Total price: " + totalSellingPrice + "€\n";
	    	
			// Check stock level
			for(Pair<Product, Spinner<Integer>> product : form){
				if(product.getKey().getStockLevel() < product.getValue().getValue())
					errorMessage += "Not enough inventory for product <" + product.getKey().getName() + "> inv level:" + product.getKey().getStockLevel() + "\n";
			}
			
	    	return errorMessage;
	    }
	    
}

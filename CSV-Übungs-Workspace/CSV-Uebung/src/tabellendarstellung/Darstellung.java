package tabellendarstellung;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Darstellung extends Application implements EventHandler<ActionEvent> {

	public static void main(String[] args) {

		launch(args);

	}

	public void start(Stage primaryStage) {
		StackPane pane = new StackPane();			//Anlegen einer Scene um die
		Scene scene = new Scene(pane);				//Tabelle darstellen zu koennen
		
		pane.getChildren().add(initTabelle());		//Initialisierung der Tabelle
		
		
		GridPane gp = new GridPane();													//Anlegen einer alternativen Scene
		Scene startScene = new Scene(gp);												//um die Pfadabfrage zu ermoeglichen
		
		gp.setVgap(20);																	//Darstellung
		gp.setHgap(20);																	//der Inhalte in der
		gp.setPadding(new Insets(0, 20, 20, 0));										//Pfadabfrage anpassen
		
		Button loadData = new Button("Datei laden");
		TextField path = new TextField();
		path.setMinWidth(500);
		gp.add(new Text("Bitte den absoluten Pfad der CSV-Datei eingeben "), 1, 1); 	//Elemente
		gp.add(path, 2, 1);																//zur Darstellung
		gp.add(loadData, 3, 1);															//hinzufuegen
		
		path.setOnKeyPressed(new EventHandler<KeyEvent>()								//Aktion, die ausgefuehrt wird wenn
	    {																				//die Entertaste nach einer Eingabe im
	        @SuppressWarnings("unchecked")												//Textfeld gedrueckt wird
			@Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	if (getCSVData(path.getText().toString(), (TableView<Zeile>) pane.getChildren().get(0))) {	//Wechselt die Scene zur
						primaryStage.setScene(scene);															//Darstellung der Tabelle
						primaryStage.setWidth(scene.getWidth());												//wenn die Datei existiert
					}
	            }
	        }
	    });
		
		loadData.setOnAction(new EventHandler<ActionEvent>() {													//Selbe Aktion wie wenn
																												//die Entertaste getaetigt
			@SuppressWarnings("unchecked")																		//wird, nur als Button
			@Override
			public void handle(ActionEvent event) {
				
				if (getCSVData(path.getText().toString(), (TableView<Zeile>) pane.getChildren().get(0))) {
					primaryStage.setScene(scene);
					primaryStage.setWidth(scene.getWidth());
				}
			}

			
		});
			
		primaryStage.setScene(startScene);			//setzen der Scene auf die Pfadabfrage beim Start des Programmes
		primaryStage.show();
	}

	@Override
	public void handle(ActionEvent arg0) {

	}

	@SuppressWarnings("unchecked")
	public TableView<Zeile> initTabelle() {												//Initialisiert die Tabelle, deren Spalten
																						//und stellt ein, dass man sie bearbeiten kann
		TableView<Zeile> table = new TableView<Zeile>();
		table.setEditable(true);
		
		TableColumn<Zeile, String> hauptartikelnr										//Erstellt die erste Spalte der Tabelle
				= new TableColumn<Zeile, String>("Hauptartikelnr");
		hauptartikelnr.setCellValueFactory(new PropertyValueFactory<>("artikelnr"));	//Weist der Spalte die Variable der 
																						//Objekte zu, die sie auslesen soll
		
		hauptartikelnr.setCellFactory(TextFieldTableCell.forTableColumn());				//sorgt dafuer, dass
		hauptartikelnr.setOnEditCommit(													//die Zellen der Spalte
			    new EventHandler<CellEditEvent<Zeile, String>>() {						//bearbeitbar werden
			        @Override															//und auch geaendert werden
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setArtikelnr(t.getNewValue());
			         }
			     }
			 );

		TableColumn<Zeile, String> artikelname											//Erstellt die zweite Spalte der Tabelle genau
				= new TableColumn<Zeile, String>("Artikelname");						//nach dem Prinzip wie bei der erste Spalte
		artikelname.setCellValueFactory(new PropertyValueFactory<>("artikelname"));
		artikelname.setCellFactory(TextFieldTableCell.forTableColumn());
		artikelname.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setArtikelname(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> hersteller											//Erstellt die dritte Spalte der Tabelle genau
				= new TableColumn<Zeile, String>("Hersteller");							//nach dem Prinzip der ersten beiden Spalten
		hersteller.setCellValueFactory(new PropertyValueFactory<>("hersteller"));
		hersteller.setCellFactory(TextFieldTableCell.forTableColumn());
		hersteller.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setHersteller(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> beschreibung											//4. Spalte
				= new TableColumn<Zeile, String>("Beschreibung");
		beschreibung.setCellValueFactory(new PropertyValueFactory<>("beschr"));
		beschreibung.setCellFactory(TextFieldTableCell.forTableColumn());
		beschreibung.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setBeschr(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> materialangaben									//...
				= new TableColumn<Zeile, String>("Materialangaben");
		materialangaben.setCellValueFactory(new PropertyValueFactory<>("materialangaben"));
		materialangaben.setCellFactory(TextFieldTableCell.forTableColumn());
		materialangaben.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setMaterialangaben(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> geschlecht
				= new TableColumn<Zeile, String>("Geschlecht");
		geschlecht.setCellValueFactory(new PropertyValueFactory<>("geschlecht"));
		geschlecht.setCellFactory(TextFieldTableCell.forTableColumn());
		geschlecht.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setGeschlecht(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> produktart
				= new TableColumn<Zeile, String>("Produktart");
		produktart.setCellValueFactory(new PropertyValueFactory<>("produktart"));
		produktart.setCellFactory(TextFieldTableCell.forTableColumn());
		produktart.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setProduktart(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> aermel
				= new TableColumn<Zeile, String>("\u00c4rmel");
		aermel.setCellValueFactory(new PropertyValueFactory<>("aermel"));
		aermel.setCellFactory(TextFieldTableCell.forTableColumn());
		aermel.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setAermel(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> bein
				= new TableColumn<Zeile, String>("Bein");
		bein.setCellValueFactory(new PropertyValueFactory<>("bein"));
		bein.setCellFactory(TextFieldTableCell.forTableColumn());
		bein.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setBein(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> kragen
				= new TableColumn<Zeile, String>("Kragen");
		kragen.setCellValueFactory(new PropertyValueFactory<>("kragen"));
		kragen.setCellFactory(TextFieldTableCell.forTableColumn());
		kragen.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setKragen(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> herstellung
				= new TableColumn<Zeile, String>("Herstellung");
		herstellung.setCellValueFactory(new PropertyValueFactory<>("herstellung"));
		herstellung.setCellFactory(TextFieldTableCell.forTableColumn());
		herstellung.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setHerstellung(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> taschenart
				= new TableColumn<Zeile, String>("Taschenart");
		taschenart.setCellValueFactory(new PropertyValueFactory<>("taschenart"));
		taschenart.setCellFactory(TextFieldTableCell.forTableColumn());
		taschenart.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setTaschenart(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> grammatur
				= new TableColumn<Zeile, String>("Grammatur");
		grammatur.setCellValueFactory(new PropertyValueFactory<>("grammatur"));
		grammatur.setCellFactory(TextFieldTableCell.forTableColumn());
		grammatur.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setGrammatur(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> material
				= new TableColumn<Zeile, String>("Material");
		material.setCellValueFactory(new PropertyValueFactory<>("material"));
		material.setCellFactory(TextFieldTableCell.forTableColumn());
		material.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setMaterial(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> ursprungsland
				= new TableColumn<Zeile, String>("Ursprungsland");
		ursprungsland.setCellValueFactory(new PropertyValueFactory<>("land"));
		ursprungsland.setCellFactory(TextFieldTableCell.forTableColumn());
		ursprungsland.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setLand(t.getNewValue());
			         }
			     }
			 );
		
		TableColumn<Zeile, String> bildname
				= new TableColumn<Zeile, String>("Bildname");
		bildname.setCellValueFactory(new PropertyValueFactory<>("bildname"));
		bildname.setCellFactory(TextFieldTableCell.forTableColumn());
		bildname.setOnEditCommit(
			    new EventHandler<CellEditEvent<Zeile, String>>() {
			        @Override
			        public void handle(CellEditEvent<Zeile, String> t) {
			             ((Zeile) t.getTableView().getItems().get(
			                 t.getTablePosition().getRow())
			                 ).setBildname(t.getNewValue());
			         }
			     }
			 );
		
		//fuegt der Tabelle die Spalten hinzu
		table.getColumns().addAll(hauptartikelnr, artikelname, hersteller, beschreibung, materialangaben, geschlecht, 
				 produktart, aermel, bein, kragen, herstellung, taschenart, grammatur, material, ursprungsland, bildname);
		 
		return table;
	}

	//Liest die CSV-Datei aus wenn sie existiert, wenn nicht wird ein Fehlermeldungsfenster geoeffnet
	public boolean getCSVData(String path, TableView<Zeile> tabelle) {
		
		BufferedReader br = null;												//initialisiert die noetigen Mittel zum auslesen
		String line = "", datensatz = "";
		ObservableList<String> liste = FXCollections.observableArrayList();
		liste.clear();
		
		try {
            br = new BufferedReader(new FileReader(path));									//auslesen der Datei
            br.readLine();																	//ueberlesen der ersten Zeile (Spaltennamen)
            
            while ((line = br.readLine()) != null) {										//solange die Datei nicht zu ende ist
            	datensatz += line;															//Fuegt die Zeile zum Rest der Davorigen hinzu
            	
            	
            	if (datensatz.contains(".jpg")) {											
            		String[] inhalte;
            		inhalte = datensatz.split(".jpg")[0].split(";");						//Trennt einen Datensatz vom Rest
            		
            		if (inhalte.length > 16) {												//Faesst die Beschreibung zusammen, sollte diese
            			String beschreibung ="";											//auf mehrere Listenfelder verteilt sein
            			int laengeBeschreibung = inhalte.length - 16;
            			for (int i = 0; i < laengeBeschreibung; i++) {
            				beschreibung += inhalte[3+i] + ";";
            			}
            			beschreibung = beschreibung.substring(0, beschreibung.length()-2);	//entfernt ein Semikolon, das zu viel ist
            			
            			String[] neueZeile = new String[16];								//schreibt den Datensatz richtig in eine
            			neueZeile[0] = inhalte[0];											//genormte Liste
            			neueZeile[1] = inhalte[1];
            			neueZeile[2] = inhalte[2];
            			neueZeile[3] = beschreibung;
            			for (int z = 4; z < 15; z++) {
            				neueZeile[z] = inhalte[z+laengeBeschreibung];
            			}
            			neueZeile[15] += ".jpg";											//fuegt ein ".jpg" am Ende hinzu, da dies beim
            																				//trennen zuvor weggefallen ist
            			for (String string : neueZeile) {
							if (string == null) {
								string = "";												//initialisiert leere Felder, damit kein
							}																//Fehler auftritt
						}
            			tabelle.getItems().add(new Zeile(neueZeile));						//Fuegt den vollstaendigen Datensatz der
            																				//Tabelle hinzu
            			
            		}else {																	//Sollten nur die Normalanzahl an Feldern
            			inhalte[inhalte.length-1] += ".jpg";								//vorhanden sein, wird das ".jpg" hinzugefuegt,
            			for (String string : inhalte) {										//das weggefallen ist,
							if (string == null) {											//die leeren Felder initialisiert
								string = "";												//
							}																//
            			}																	//
            			tabelle.getItems().add(new Zeile(inhalte));							//und alles zum Schluss wieder als Datensatz
            		}																		//in die Tabelle geschrieben
            		
            		String[] stringteil = datensatz.split(".jpg");							//Sollte ein Restwert vom zusammensetzen
            																				//der ausgelesenen Zeilen uebrig bleiben
                	if (stringteil.length == 1) {											//wird dieser wieder hinzugefuegt
                		datensatz = "";
                	}else {
                		datensatz = datensatz.split(".jpg")[1];
                	}
            	}	
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            callErrorWindow();
            return false;
            
        } catch (IOException e) {
            e.printStackTrace();
            
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    
                }
            }
        }
		return true;
	}
	
	//Oeffnet ein Fehlermeldungsfenster, das angezeigt werden kann, wenn die angegebene CSV-Datei nicht existiert
	public void callErrorWindow() {
		
		Stage meldung = new Stage();							//Legt notwendige Objekte an
		GridPane gp = new GridPane();
		Button closeWindow = new Button("Schlie\u00dfen");
		
		gp.setVgap(10);											//Aussehen des Fensters
		gp.setPadding(new Insets(10, 10, 10, 10));				//anpassen
		GridPane.setHalignment(closeWindow, HPos.CENTER);
		
		Scene fehlerScene = new Scene(gp);
		closeWindow.setOnAction(new EventHandler<ActionEvent>() {	//schliesst das Fenster wenn der Knopf gedrueckt wird

			@Override
			public void handle(ActionEvent event) {
				meldung.close();
			}
		});
		gp.add(new Text("Datei wurde nicht gefunden!"), 0, 0);		//"bastelt" alles zusammen
		gp.add(closeWindow, 0, 1);									//
		meldung.setScene(fehlerScene);								//
		meldung.show();												//und zeigt es an
	}
}

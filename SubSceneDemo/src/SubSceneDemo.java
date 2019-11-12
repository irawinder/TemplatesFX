import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * SubSceneDemo Demo Extends a JavaFX Application to show how to manage multiple dynamic content containers
 * 
 * @author Ira Winder
 *
 */
public class SubSceneDemo extends Application {
	
	// Scene Configuration within Window
	
	///////////////////////////////////////
	//		toolbar						 //
	///////////////////////////////////////
	//		version			//			 //
	//////////////////////////	outcome  //
	//						//			 //
	//		canvas			//			 //
	//						///////////////
	//////////////////////////			 //
	//						//	commit	 //
	//		massing  		//			 //
	//						//			 //
	///////////////////////////////////////
	//		status						 //
	///////////////////////////////////////
	
	// All Default Dimensions are in Pixels:
	
	final private double DEFAULT_APPLICATION_WIDTH = 1280;
	final private double DEFAULT_APPLICATION_HEIGHT = 800;
	
	final private double TOOL_HEIGHT    = 30;
	final private double VERSION_HEIGHT = 50;
	final private double STATUS_HEIGHT  = 30;
	final private double OUTCOME_WIDTH  = 400;
	
	public static void main(String[] args) {
        launch();
    }
	
    @Override
    public void start(Stage appWindow) {
		
		// Example JavaFX Node-based content for each SubScene
		StackPane toolbarContent = new StackPane(new Label("Toolbar"));
		StackPane versionContent = new StackPane(new Label("Version Tree"));
		StackPane canvasContent  = new StackPane(new Label("Visual Programming Canvas"));
		StackPane massingContent = new StackPane(new Label("3D Massing"));
		StackPane outcomeContent = new StackPane(new Label("Performance Graph"));
		StackPane commitContent  = new StackPane(new Label("Commit Scenario"));
		StackPane statusContent  = new StackPane(new Label("Status"));
		
		// Content Containers for the Application (Populate these however you wish)
		SubScene toolbar;
		SubScene version;
		SubScene canvas;
		SubScene massing;
		SubScene outcome;
		SubScene commit;
		SubScene status;
		
		// Initialize the SubScenes with Content
		toolbar = new SubScene(
				toolbarContent, 
				DEFAULT_APPLICATION_WIDTH, 
				TOOL_HEIGHT);
		version = new SubScene(
				versionContent, 
				DEFAULT_APPLICATION_WIDTH - OUTCOME_WIDTH, 
				VERSION_HEIGHT);
		canvas = new SubScene(
				canvasContent, 
				DEFAULT_APPLICATION_WIDTH - OUTCOME_WIDTH,
				0.5 * (DEFAULT_APPLICATION_HEIGHT - TOOL_HEIGHT - VERSION_HEIGHT - STATUS_HEIGHT));
		massing = new SubScene(
				massingContent, 
				DEFAULT_APPLICATION_WIDTH - OUTCOME_WIDTH, 
				0.5 * (DEFAULT_APPLICATION_HEIGHT - TOOL_HEIGHT - VERSION_HEIGHT - STATUS_HEIGHT), 
				true, SceneAntialiasing.BALANCED);
		outcome = new SubScene(
				outcomeContent, 
				OUTCOME_WIDTH, 
				0.5 * (DEFAULT_APPLICATION_HEIGHT - TOOL_HEIGHT - STATUS_HEIGHT));
		commit = new SubScene(
				commitContent, 
				OUTCOME_WIDTH, 
				0.5 * (DEFAULT_APPLICATION_HEIGHT - TOOL_HEIGHT - STATUS_HEIGHT));
		status = new SubScene(
				statusContent, 
				DEFAULT_APPLICATION_WIDTH, 
				STATUS_HEIGHT);
        
		// Organize the SubScenes into Nested Grid Panes
        GridPane windowPane = new GridPane();
        GridPane mainPane = new GridPane();
        GridPane leftPane = new GridPane();
        GridPane rightPane = new GridPane();
        
        ///////////////////////////////////////
    	//						  			 //
    	//						  			 //
    	//						  			 //
    	//		windowPane					 //        
    	//						  			 //   
    	//						  			 //
    	//						  			 //
    	//						  			 //        
    	//						  			 //        
    	//						  			 //        
    	//						  			 //
    	///////////////////////////////////////
        
        // Window GridPane (windowPane)
        windowPane.add(toolbar, 0, 0);
        windowPane.add(mainPane, 0, 1);
        windowPane.add(status, 0, 2);
        
        ///////////////////////////////////////
    	//		toolbar					     //
    	///////////////////////////////////////
    	//						  			 //
    	//						  			 //
    	//						  			 //
    	//		mainPane					 //        
    	//						  			 //        
    	//						  			 //        
    	//						  			 //
    	///////////////////////////////////////
    	//		status						 //
    	///////////////////////////////////////
        
        // Main GridPane (mainPane)
        mainPane.add(leftPane, 0, 0);
        mainPane.add(rightPane, 1, 0);
        
        ///////////////////////////////////////
    	//						//			 //
    	//		leftPane		// rightPane //
    	//						//			 //
    	//						//			 //
    	//						//			 //
    	//						//			 //
    	//						//	 		 //
    	//				  		//			 //
    	//						//			 //
    	///////////////////////////////////////
        
        // Left GridPane (leftPane)
        leftPane.add(version, 0, 0);
        leftPane.add(canvas, 0, 1);
        leftPane.add(massing, 0, 2);
        
        //////////////////////////
    	//		version			//
    	//////////////////////////
    	//						//
    	//		canvas			//
    	//						//
    	//////////////////////////			 
    	//						//
    	//		massing  		//			 
    	//						//			 
    	//////////////////////////
        
        // Right GridPane (rightPane)
        rightPane.add(outcome, 0, 0);
        rightPane.add(commit, 0, 1);
        
        ///////////////
    	//			 //
    	//	outcome  //
    	//			 //
    	//			 //
    	///////////////
    	//			 //
    	//	commit	 //
    	//			 //
    	//			 //
    	///////////////
        
        // Commit all content to a master scene
        Scene allContent = new Scene(windowPane);
        
        // Handle event for resizing the width of the application content
        allContent.widthProperty().addListener(new ChangeListener<Number>() {
        	
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			double windowWidth = (double) arg2;
    			toolbar.setWidth(windowWidth);
    	    	version.setWidth(windowWidth - OUTCOME_WIDTH);
    	    	canvas.setWidth(windowWidth - OUTCOME_WIDTH);
    	    	massing.setWidth(windowWidth - OUTCOME_WIDTH);
    	    	status.setWidth(windowWidth);
    		}
        });
        
        // Handle event for resizing the height of the application content
        allContent.heightProperty().addListener(new ChangeListener<Number>() {
        	
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			double windowHeight = (double) arg2;
    			canvas.setHeight(0.5*(windowHeight - TOOL_HEIGHT - VERSION_HEIGHT - STATUS_HEIGHT));
    	    	massing.setHeight(0.5*(windowHeight - TOOL_HEIGHT - VERSION_HEIGHT - STATUS_HEIGHT));
    	    	outcome.setHeight(0.5*(windowHeight - TOOL_HEIGHT - STATUS_HEIGHT));
    	    	commit.setHeight(0.5*(windowHeight - TOOL_HEIGHT - STATUS_HEIGHT));
    		}
        });
        
        appWindow.setScene(allContent);
        appWindow.show();
    }

}
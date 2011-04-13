package org.weathersystem.weatherstation.southafrica.client;

import org.restlet.client.data.MediaType;
import org.restlet.client.data.Preference;
import org.restlet.client.resource.Result;

import org.weathersystem.weatherstation.southafrica.common.Weather;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WeatherStationSouthAfrica implements EntryPoint {
    /**
     * Create a remote service proxy to talk to the server-side Weather
     * resource.
     */
    
    private final WeatherResourceProxy weatherResource = GWT
    .create(WeatherResourceProxy.class);
    
    /**
     * This is the entry point method.
     */
    @Override
	public void onModuleLoad() {
        final Button getButton = new Button("get");
      //  final Button updateButton = new Button("update");

        // Weather fields
        final TextBox temp = new TextBox();
        
        // Home address fields
        final TextBox haTbLine1 = new TextBox();
        final TextBox haTbLine2 = new TextBox();
        final TextBox haTbZipCode = new TextBox();
        final TextBox haTbCity = new TextBox();
        final TextBox haTbCountry = new TextBox();

        VerticalPanel contactPanel = new VerticalPanel();
        contactPanel.add(temp);

        RootPanel.get("contactContainer").add(contactPanel);

        VerticalPanel homeAddressPanel = new VerticalPanel();
        homeAddressPanel.add(haTbLine1);
        homeAddressPanel.add(haTbLine2);
        homeAddressPanel.add(haTbZipCode);
        homeAddressPanel.add(haTbCity);
        homeAddressPanel.add(haTbCountry);
        //RootPanel.get("homeAddressContainer").add(homeAddressPanel);

        RootPanel.get("buttonContainer").add(getButton);
        //RootPanel.get("buttonContainer").add(updateButton);

   
        // Focus the cursor on the first name field when the app loads
        temp.setFocus(true);
        //updateButton.setVisible(false);

        // Define a dialog box
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        closeButton.getElement().setId("closeButton");
        final Label textToServerLabel = new Label();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            @Override
			public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        // Get the contact
        getButton.addClickHandler(new ClickHandler() {
            @Override
			public void onClick(ClickEvent event) {
                // Set up the contact resource
                weatherResource.getClientResource().setReference(
                        "/weatherdata/temp");
                weatherResource.getClientResource().getClientInfo()
                        .getAcceptedMediaTypes().add(
                                new Preference<MediaType>(
                                        MediaType.APPLICATION_JAVA_OBJECT_GWT));

                // Retrieve the contact
                weatherResource.retrieve(new Result<Weather>() {
                    @Override
					public void onFailure(Throwable caught) {
                        dialogBox.setText("Get contact");
                        textToServerLabel.setText("Error: "
                                + caught.getMessage());
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }

                    @Override
					public void onSuccess(Weather weather) {

                      
                   
          //              updateButton.setVisible(true);
                    }
                });
            }
        });
/*
        updateButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Contact contact = new Contact();
               // contact.setFirstName(cTbFirstName.getValue());
                contact.setLastName(cTbLastName.getValue());
                try {
                    contact.setAge(Integer.parseInt(cTbAge.getValue()));
                } catch (Exception e) {
                    cTbAge.addStyleName("error");
                    dialogBox.setText("Update contact");
                    textToServerLabel.setText("Error: Invalid age specified.");
                    dialogBox.center();
                    closeButton.setFocus(true);
                    return;
                }

                Address homeAddress = null;
                if (haTbLine1.getValue() != null
                        || haTbLine2.getValue() != null
                        || haTbZipCode.getValue() != null
                        || haTbCity.getValue() != null
                        || haTbCountry.getValue() != null) {
                    homeAddress = new Address();
                    homeAddress.setLine1(haTbLine1.getValue());
                    homeAddress.setLine2(haTbLine2.getValue());
                    homeAddress.setZipCode(haTbZipCode.getValue());
                    homeAddress.setCity(haTbCity.getValue());
                    homeAddress.setCountry(haTbCountry.getValue());
                }
                contact.setHomeAddress(homeAddress);

                // Update the contact
                contactResource.store(contact, new Result<Void>() {
                    public void onFailure(Throwable caught) {
                        dialogBox.setText("Update contact");
                        textToServerLabel.setText("Error: "
                                + caught.getMessage());
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }

                    public void onSuccess(Void v) {
                        dialogBox.setText("Update contact");
                        textToServerLabel
                                .setText("Contact successfully updated");
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }
                });
                
            }
            
        });
        */
    }
}

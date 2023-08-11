package com.fnunezzz.pdfmicrosservice.classes.registrationForm;

import com.fnunezzz.pdfmicrosservice.classes.AbstractPrintObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RegistrationForm extends AbstractPrintObject {

    private Person client;

    private Address address;

    private Contact contact;

    private String notes;

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String buildJsonObject(String templateName) throws Exception {
        anyNull(this);

        JsonObject root = new JsonObject();
        JsonObject content = new JsonObject();
        JsonObject client = new JsonObject();
        JsonObject address = new JsonObject();
        JsonObject contact = new JsonObject();


        client.addProperty("firstName", this.client.getFirstName());
        client.addProperty("middleName", this.client.getMiddleName());
        client.addProperty("lastName", this.client.getLastName());
        client.addProperty("birthDate", this.client.getBirthDate());

        address.addProperty("street", this.address.getStreet());
        address.addProperty("zipCode", this.address.getZipCode());
        address.addProperty("number", this.address.getNumber());
        address.addProperty("city", this.address.getCity());
        address.addProperty("state", this.address.getState());

        contact.addProperty("phoneNumber", this.contact.getPhoneNumber());
        contact.addProperty("email", this.contact.getEmail());

        content.add("address", address);
        content.add("client", client);
        content.add("contact", contact);
        content.addProperty("notes", this.notes);

        root.add(templateName, content);
        Gson gson = new Gson();
        return gson.toJson(root);
    }
}

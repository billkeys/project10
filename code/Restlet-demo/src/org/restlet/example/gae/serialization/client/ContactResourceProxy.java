package org.restlet.example.gae.serialization.client;

import org.restlet.client.resource.ClientProxy;
import org.restlet.client.resource.Delete;
import org.restlet.client.resource.Get;
import org.restlet.client.resource.Put;
import org.restlet.client.resource.Result;
import org.restlet.example.common.Contact;

public interface ContactResourceProxy extends ClientProxy {
    @Get
    public void retrieve(Result<Contact> callback);

    @Put
    public void store(Contact contact, Result<Void> callback);

    @Delete
    public void remove(Result<Void> callback);

}

package edu.gac.polisci.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BlobServiceAsync {
	void getBlobStoreUploadUrl(AsyncCallback<String> callback);

}

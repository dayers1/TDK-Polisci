package edu.gac.polisci.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import edu.gac.polisci.shared.Thesis;


//The FormPanel must submit to a servlet that extends HttpServlet  
//  so HttpServlet must be used
@SuppressWarnings("serial")
public class SubmitThesisHTTPServiceImpl extends HttpServlet {
	//Start Blobstore 
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	//Override the doPost method to store the Blob's meta-data
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		@SuppressWarnings("deprecation")
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("upload");

		//Get the parameters from the request to post the ad
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String professor = req.getParameter("professor");
		String year = req.getParameter("year");
		String semester = req.getParameter("semester");
		String cl = req.getParameter("class");
		String tags = req.getParameter("tags");
		String ta = req.getParameter("abstract");
		
		String url=null;

		
		//Map the thesisURL to the blobservice servlet, which will serve the thesis
		
		//NOTE NOTE NOTE NOTE NOTE
		//The line below is currently edited to avoid an error.
		//I BELIEVE the error occurs when someone submits a thesis with no PDF.
		url = "/thesisdb/blobservice?blob-key=" + blobKey.getKeyString();
		Thesis thesis = new Thesis(title, author, professor, year, semester, cl, ta,
					url, tags);
		ThesisDBModel.storeThesis(thesis);
		}
	}



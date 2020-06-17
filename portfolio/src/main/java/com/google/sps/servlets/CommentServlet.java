package com.google.sps.servlets;

import com.google.sps.classes.Comment;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add-comment")
public class CommentServlet extends HttpServlet {
  
 @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    List<Comment> commentList = new ArrayList<>();

    for (Entity entity: results.asIterable()) {

      String name = (String) entity.getProperty("name");
      String message = (String) entity.getProperty("message");
      Date time = (Date) entity.getProperty("timestamp");

      commentList.add(new Comment(name, message, time));
    }

    int numCommentsRequested = getNumComments(request);

     if (commentList.size() > numCommentsRequested){
      commentList = commentList.subList(0, numCommentsRequested);
    }

    String json = convertToJsonUsingGson(commentList);

    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  private int getNumComments(HttpServletRequest request){
    String userChoiceString = request.getParameter("commentsQuantity");
    
    int userChoice;
    userChoice = Integer.parseInt(userChoiceString); 

    if (userChoice < 1) {
      throw new IllegalArgumentException("Player choice is out of range: " + userChoice);
    }

    return userChoice;
  }


  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String name = getParameter(request, "name-input", "anonymous");
    String message = getParameter(request, "message", "empty comment");

    Entity newComment = new Entity("Comment");

    newComment.setProperty("name", name);
    newComment.setProperty("message", message);
    newComment.setProperty("timestamp", new Date());

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(newComment);

    response.sendRedirect("https://8080-03adb9af-0470-4f43-b5b3-40607f387072.us-west1.cloudshell.dev/?authuser=0");
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    return ((value == null) ? defaultValue : value);
  }

  private String convertToJsonUsingGson(List<Comment> commentList) {
    Gson gson = new Gson();
    return gson.toJson(commentList);
  }

}
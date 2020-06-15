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

@WebServlet("/add-country")
public class CommentServlet extends HttpServlet {
  
 @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

   
    }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    
    response.sendRedirect("https://8080-03adb9af-0470-4f43-b5b3-40607f387072.us-west1.cloudshell.dev/?authuser=0");
  }

}
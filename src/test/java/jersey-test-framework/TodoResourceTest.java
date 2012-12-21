
import com.example.sjs.beans.TodoBean;
import com.example.sjs.data.TodoDAO;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import scala.Option;
import scala.Some;

import java.lang.*;
import java.lang.IllegalAccessException;
import java.lang.Integer;
import java.lang.String;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.System;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.UUID;


public class TodoResourceTest extends JerseyTest{

    private TodoDAO dao = new TodoDAO();

    public TodoResourceTest() throws Exception {
        super(new WebAppDescriptor.Builder("com.example.sjs.resources")
                .contextPath("sjs")
                .build());
    }


    @Test
    public void testGetTodoResource() throws Exception {

        // Random strings for Todo fields
        final String str1 = UUID.randomUUID().toString();
        final String str2 = UUID.randomUUID().toString();

        // Create a Todo in the database using the DAO
        TodoBean testTodo = new TodoBean("",str1,str2); // 'id' auto-assigned by database
        TodoBean dbTodo = dao.create(testTodo);

        // Convert Todo to JSON
        ObjectMapper mapper = new ObjectMapper();
        String dbTodoJSONString = mapper.writeValueAsString(dbTodo);

        WebResource webResource = resource();
        WebResource url = webResource.path("todos/" + dbTodo.getId());
        try {
            String s = url.get(String.class);
            Assert.assertEquals(dbTodoJSONString, s);
        } catch (UniformInterfaceException e) {
            // FIXME - Handle HTTP errors 300+ here
            System.out.print(e);
        }

        // Remove the test entry from the database
        int id = Integer.parseInt(dbTodo.getId());
        dao.remove(id);

    }


    @Test
    public void testMultipleTodoResource() throws Exception {
        WebResource webResource = resource();
//        String s = webResource.path("todos").accept("application/json").get(String.class);
//        System.out.print(s);
    }



}
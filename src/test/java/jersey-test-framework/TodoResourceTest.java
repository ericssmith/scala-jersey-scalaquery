
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

public class TodoResourceTest extends JerseyTest{

    public TodoResourceTest() throws Exception {
        super(new WebAppDescriptor.Builder("com.example.sjs.resources")
                .contextPath("sjs")
                .build());
    }

    @Test
    public void testTodoResource() throws Exception {
        WebResource webResource = resource();
        String responseMsg = webResource.path("todos").get(String.class);
        Assert.assertEquals("Hello World", responseMsg);
    }

}
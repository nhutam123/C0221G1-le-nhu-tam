import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet" ,urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float numberOne=Float.parseFloat(request.getParameter("firstNumber"));
        float numberTwo=Float.parseFloat(request.getParameter("secondNumber"));
        String operator=request.getParameter("option");
        float result;
        if (operator.equals("cong")){
            result=numberOne+numberTwo;
        }else if (operator.equals("tru")){
            result=numberOne-numberTwo;
        }else {
            result=numberOne*numberTwo;
        }
        String a = numberOne+" "+operator+" "+numberTwo +" = "+result;
        request.setAttribute("result",a);
        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }
}

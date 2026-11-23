
package com.bits.leave;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LeaveServiceApplication {

    public static void main(String[] args) throws Exception {

        LeaveCalculator calculator = new LeaveCalculator();

        String envValue = System.getenv("APP_ENV");

final String environment =
        (envValue == null) ? "local" : envValue;

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            int remainingLeave =
                    calculator.calculateLeaveBalance(20, 5);

            double encashment =
                    calculator.calculateEncashment(30000, 5);

            String response =
                    "Employee Leave Service\n" +
                    "Environment: " + environment + "\n" +
                    "Total Leave: 20\n" +
                    "Leave Taken: 5\n" +
                    "Remaining Leave: " + remainingLeave + "\n" +
                    "Monthly Salary: 30000\n" +
                    "Encashable Days: 5\n" +
                    "Encashment Amount: " + encashment + "\n";

            byte[] bytes =
                    response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                    "Content-Type", "text/plain; charset=UTF-8");

            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });

        server.start();

        System.out.println(
                "Leave Service running on port 8080");
        System.out.println("Environment: " + environment);
    }
}


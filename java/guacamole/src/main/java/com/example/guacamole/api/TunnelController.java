package com.example.guacamole.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TunnelController extends GuacamoleHTTPTunnelServlet {

    @Override
    @RequestMapping(path = "/tunnel", method = { RequestMethod.POST, RequestMethod.GET })
    protected void handleTunnelRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        super.handleTunnelRequest(request, response);
    }

    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol("ssh");
        config.setParameter("hostname", "localhost");
        config.setParameter("port", "7777");

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket("localhost", 4822),
                config);

        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);

        // Attach tunnel to session
        // HttpSession httpSession = request.getSession(true);
        // GuacamoleSession session = new GuacamoleSession(httpSession);
        // session.attachTunnel(tunnel);

        // Return pre-attached tunnel
        return tunnel;
    }

}

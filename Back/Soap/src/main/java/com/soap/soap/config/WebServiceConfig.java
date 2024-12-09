package com.soap.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public XsdSchema librosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/libros.xsd"));
    }

    @Bean
    public XsdSchema usuariosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/usuarios.xsd"));
    }

    @Bean
    public XsdSchema prestamosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/prestamos.xsd"));
    }

    @Bean
    public XsdSchema reportesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/reportes.xsd"));
    }
    
    @Bean(name = "libros")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema librosSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("librosPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.soap.com/");
        wsdl11Definition.setSchema(librosSchema());
        return wsdl11Definition;
    }

    @Bean(name = "usuarios")
    public DefaultWsdl11Definition usuariosDefinition(XsdSchema librosSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("usuariosPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.soap.com/");
        wsdl11Definition.setSchema(librosSchema());
        return wsdl11Definition;
    }

    @Bean(name = "prestamos")
    public DefaultWsdl11Definition prestamosDefinition(XsdSchema librosSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("prestamosPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.soap.com/");
        wsdl11Definition.setSchema(librosSchema());
        return wsdl11Definition;
    }

    @Bean(name = "reportes")
    public DefaultWsdl11Definition reportesDefinition(XsdSchema librosSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("reportesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.soap.com/");
        wsdl11Definition.setSchema(librosSchema());
        return wsdl11Definition;
    }
}

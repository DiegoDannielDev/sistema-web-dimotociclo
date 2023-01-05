module br.com.project.dimotocicloapi {
  requires javafx.controls;
  requires javafx.fxml;
  requires com.dlsc.formsfx;
  requires org.kordamp.bootstrapfx.core;
  requires spring.context;
  requires lombok;
  requires spring.core;
  requires spring.web;
  requires spring.boot.starter.web;
  requires spring.webmvc;
  requires gson;
  requires java.sql;
  requires com.fasterxml.jackson.databind;
  requires aspose.barcode;
  requires controlsfx;
  requires spring.boot.autoconfigure;

  opens br.com.project.dimotocicloapi to
      javafx.fxml;

  exports br.com.project.dimotocicloapi;
  exports br.com.project.dimotocicloapi.adapters.controllers;
  exports br.com.project.dimotocicloapi.adapters.resttemplate.request;
  exports br.com.project.dimotocicloapi.domain.model;

  opens br.com.project.dimotocicloapi.adapters.controllers to
      javafx.fxml;
  opens br.com.project.dimotocicloapi.adapters.resttemplate.request to
      gson;
  opens br.com.project.dimotocicloapi.adapters.resttemplate.response to
      gson;
}

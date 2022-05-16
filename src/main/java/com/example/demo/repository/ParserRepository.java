package com.example.demo.repository;

import com.example.demo.modules.SheetType;
import java.io.IOException;
import org.json.JSONObject;

public interface ParserRepository {
  JSONObject parseCN() throws IOException;
  JSONObject parseCR() throws IOException;
  JSONObject parseCT() throws IOException;
}

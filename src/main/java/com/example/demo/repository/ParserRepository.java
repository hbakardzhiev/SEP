package com.example.demo.repository;

import com.example.demo.modules.SheetType;
import java.io.IOException;
import org.json.JSONObject;

public interface ParserRepository {

  public JSONObject parseElements();

  public JSONObject parseCR(SheetType type) throws IOException;
}

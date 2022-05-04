package com.example.demo.modules;

import com.example.demo.repository.ParserRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class Parser implements ParserRepository {

  private final Document document;

  public Parser() throws IOException {
    final var input = new File(getClass().getClassLoader().getResource("example.html").getFile());
    document = Jsoup.parse(input, "UTF-8");
  }

  private String parseAtrrID(String id) {
    return this.document.select("[attrid=" + id + "]").text();
  }

  public CN parseElement() {
    final var cn = new CN();
    cn.setDescription(parseAtrrID("description"));
    cn.setChangeType(parseAtrrID("phiChangeType").split(", "));
    cn.setCustomerApproval(parseAtrrID("customerApprovalRequired").equalsIgnoreCase("Yes"));
    cn.setSupplierApproval(parseAtrrID("supplierApprovalRequired").equalsIgnoreCase("Yes"));
    cn.setPhilipsID(parseAtrrID("phiProjectId"));
    cn.setTeamName(parseAtrrID("teamTemplate.name"));
    cn.setChangeAdminAuditReq(parseAtrrID("phiCNAudit").equalsIgnoreCase("Yes"));
    cn.setCreatedBy(parseAtrrID("iterationInfo.creator").split(", "));
    cn.setModifiedBy(parseAtrrID("modifier").split(", "));
    cn.setRegulatoryRestrictionReq(parseAtrrID("phiRegResRequired").equalsIgnoreCase("Yes"));
    return cn;
  }
}




///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nl.partyhub.myparties;
//
//import Models.Party;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
// 
//@Path("/xml/party")
//public class XmlService {
// 
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_XML)
//	public Party getPartyInXML(@PathParam("id") int id) {
// 
//		Party party = new Party();
//		party.setName("partyfeest");
//		party.setId(id);
// 
//		return party;
// 
//	}
// 
//}
SessionFactory factory = new COnfiguration().configure().buildSessionFactory();
Session session = factory.openSession();
Transaction tr = null;
try{
	tr = session.begintransaction();
	List<String> message = tr.createQuerry("fromMEssage").list();
	tr.commit();
	return list;
}
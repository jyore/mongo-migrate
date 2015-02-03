
//Drop dbs and recreate collections
useDb('test');
db.dropDatabase();
db.createCollection('foo');

useDb('other');
db.dropDatabase();
db.createCollection('bar');
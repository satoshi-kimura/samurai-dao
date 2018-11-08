samurai-dao
===========
samurai-dao is a simple, high performance object/relational persistence and query service.

## Demo
```
Dao dao = new RdbDao(dataSource);

// insert
Emp emp = new Emp();
emp.setCOMM("2");
emp.setDEPTNO("10");
emp.setEMPNO("1");
emp.setENAME("ename");
int count = dao.insert(emp);

// update
emp.setENAME("ename2");
count = dao.update(emp);

// delete
count = dao.delete(emp);

Optional<Emp> selectEmp = dao.selectOne(
	"SELECT * FROM emp WHERE empno = /*no*/0",
	args("no", 1), Emp.class);

```

## Function
### ORM(Object/Relational Mapping)
#### convention
coming soon.

#### configuration
coming soon.

### Insert
coming soon.

### Update
coming soon.

### Delete
coming soon.

### Select
coming soon.

### Dump SQL

Dump executed all SQL to tmp directory.

```
// prepare
SqlLogRegistry registry = SqlLogRegistry.getInstance();
DaoConfig config = registry.getConfig();
config.setLogMaxSize(1024); // num of SQL a thread.

// dump
registry.dump();
```

log.
```
INFO  2018-11-08 12:34:56,123 [main] Dump all SQL.[/tmp/samurai-dao-sql-3596045546078174883.dump]
```

## Requirement
* Java8+


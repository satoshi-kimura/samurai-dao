DROP TABLE EMP;
DROP TABLE DEPT;

CREATE TABLE EMP
       (EMPNO NUMERIC(4) NOT NULL PRIMARY KEY,
        ENAME VARCHAR(10),
        JOB VARCHAR(9),
        MGR NUMERIC(4),
        HIREDATE DATE,
        SAL NUMERIC(7, 2),
        COMM NUMERIC(7, 2),
        DEPTNO NUMERIC(2));

INSERT INTO EMP VALUES (7369, 'SMITH',  'CLERK',     7902,
        TO_DATE('1980-12-17', 'YYYY-MM-DD'),  800, NULL, 20);
INSERT INTO EMP VALUES (7499, 'ALLEN',  'SALESMAN',  7698,
        TO_DATE('1981-02-20', 'YYYY-MM-DD'), 1600,  300, 30);
INSERT INTO EMP VALUES (7521, 'WARD',   'SALESMAN',  7698,
        TO_DATE('1981-02-22', 'YYYY-MM-DD'), 1250,  500, 30);
INSERT INTO EMP VALUES (7566, 'JONES',  'MANAGER',   7839,
        TO_DATE('1981-04-02', 'YYYY-MM-DD'),  2975, NULL, 20);
INSERT INTO EMP VALUES (7654, 'MARTIN', 'SALESMAN',  7698,
        TO_DATE('1981-09-28', 'YYYY-MM-DD'), 1250, 1400, 30);
INSERT INTO EMP VALUES (7698, 'BLAKE',  'MANAGER',   7839,
        TO_DATE('1981-05-01', 'YYYY-MM-DD'),  2850, NULL, 30);
INSERT INTO EMP VALUES (7782, 'CLARK',  'MANAGER',   7839,
        TO_DATE('1981-06-09', 'YYYY-MM-DD'),  2450, NULL, 10);
INSERT INTO EMP VALUES (7788, 'SCOTT',  'ANALYST',   7566,
        TO_DATE('1982-12-09', 'YYYY-MM-DD'), 3000, NULL, 20);
INSERT INTO EMP VALUES (7839, 'KING',   'PRESIDENT', NULL,
        TO_DATE('1981-11-17', 'YYYY-MM-DD'), 5000, NULL, 10);
INSERT INTO EMP VALUES (7844, 'TURNER', 'SALESMAN',  7698,
        TO_DATE('1981-09-08', 'YYYY-MM-DD'),  1500,    0, 30);
INSERT INTO EMP VALUES (7876, 'ADAMS',  'CLERK',     7788,
        TO_DATE('1983-01-12', 'YYYY-MM-DD'), 1100, NULL, 20);
INSERT INTO EMP VALUES (7900, 'JAMES',  'CLERK',     7698,
        TO_DATE('1981-12-03', 'YYYY-MM-DD'),   950, NULL, 30);
INSERT INTO EMP VALUES (7902, 'FORD',   'ANALYST',   7566,
        TO_DATE('1981-12-03', 'YYYY-MM-DD'),  3000, NULL, 20);
INSERT INTO EMP VALUES (7934, 'MILLER', 'CLERK',     7782,
        TO_DATE('1982-01-23', 'YYYY-MM-DD'), 1300, NULL, 10);

CREATE TABLE DEPT
       (DEPTNO NUMERIC(2) NOT NULL PRIMARY KEY,
        DNAME VARCHAR(14),
        LOC VARCHAR(13),
        VERSIONNO NUMERIC(8));

INSERT INTO DEPT VALUES (10, 'ACCOUNTING', 'NEW YORK', 0);
INSERT INTO DEPT VALUES (20, 'RESEARCH',   'DALLAS', 0);
INSERT INTO DEPT VALUES (30, 'SALES',      'CHICAGO', 0);
INSERT INTO DEPT VALUES (40, 'OPERATIONS', 'BOSTON', 0);

COMMIT;

# Ejercicio 10 - Rollback

**Inserta varios empleados en una transacción y haz rollback si uno falla.**

Al principio connection.setAutoCommit(false), para desahibilitar autoCommit, y luego hacemos algunos transacciones y al final lo guardamos con connection.commit();
Si algo falla en el catch hacemos un rollback, connection.rollback();

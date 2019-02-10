# Exterminatus service deployable module

Configuration of exterminatus-service to be deployed onto application server.

Tested with Glassfish 4.0.

Depends on application server provided resource - JDBC resource with JNDI name `jdbc/exterminate`.

## Troubleshooting

- Can't run application server provided 'Tester'

Add this line to `domain.xml`

`<jvm-options>-Djavax.xml.accessExternalSchema=all</jvm-options>`

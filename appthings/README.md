# Istruzioni
Per utiltizzare il seguente progetto occorre modificare le configurazioni dell'application server. Questi file sono situati all'interno della cartella `standalone/cofigurations` dell'Application Server.

**!!Vi consiglio di creare preventivamente una copia di backup per evitare che eventuali errori di modifica compromettano il normale funzionamento del server!!**

Di seguito verranno illustrate le modifiche da fare a seconda del fatto che si stia usando per il server la configurazione *standalone* o *standalone-full*:


## standalone.xml
Le modifiche da fare sono le seguenti:

* Nella sezione `<extensions>` abilitare l'estensione di active-mq aggiugnendo:

```
<extension module="org.wildfly.extension.messaging-activemq"/>
```

* Agiungere all'interno di `<subsystem xmlns=urn:jboss:domain:ejb3:X.X>` le configurazioni per l'mdb:

```
<mdb>
	<resource-adapter-ref resource-adapter-name="${ejb.resource-adapter-name:activemq-ra.rar}"/>
	<bean-instance-pool-ref pool-name="mdb-strict-max-pool"/>
</mdb>
```

* Aggiungere all'interno della sezione `<profiles>` le configurazioni per active-mq: 

```
<subsystem xmlns="urn:jboss:domain:messaging-activemq:13.0">
	<server name="default">
		<statistics enabled="${wildfly.messaging-activemq.statistics-enabled:${wildfly.statistics-enabled:false}}"/>
		<security-setting name="#">
		<role name="guest" send="true" consume="true" create-non-durable-queue="true" delete-non-durable-queue="true"/>
		</security-setting>
		<address-setting name="#" dead-letter-address="jms.queue.DLQ" expiry-address="jms.queue.ExpiryQueue" max-size-bytes="10485760" page-size-bytes="2097152" message-counter-history-day-limit="10"/>
		<http-connector name="http-connector" socket-binding="http" endpoint="http-acceptor"/>
		<http-connector name="http-connector-throughput" socket-binding="http" endpoint="http-acceptor-throughput">
		<param name="batch-delay" value="50"/>
		</http-connector>
		<in-vm-connector name="in-vm" server-id="0">
			<param name="buffer-pooling" value="false"/>
		</in-vm-connector>
		<http-acceptor name="http-acceptor" http-listener="default"/>
		<http-acceptor name="http-acceptor-throughput" http-listener="default">
			<param name="batch-delay" value="50"/>
			<param name="direct-deliver" value="false"/>
		</http-acceptor>
		<in-vm-acceptor name="in-vm" server-id="0">
			<param name="buffer-pooling" value="false"/>
		</in-vm-acceptor>
		<jms-queue name="ExpiryQueue" entries="java:/jms/queue/ExpiryQueue"/>
		<jms-queue name="DLQ" entries="java:/jms/queue/DLQ"/>
		<jms-queue name="MessageQueue" entries="java:/jms/queue/MessageQueue"/>
		<connection-factory name="InVmConnectionFactory" entries="java:/ConnectionFactory" connectors="in-vm"/>
		<connection-factory name="RemoteConnectionFactory" entries="java:jboss/exported/jms/RemoteConnectionFactory" connectors="http-connector"/>
		<pooled-connection-factory name="activemq-ra" entries="java:/JmsXA java:jboss/DefaultJMSConnectionFactory" connectors="in-vm" transaction="xa"/>
	</server>
</subsystem>

```

## standalone-full.xml
Le modifiche da fare sono le seguenti:

* Agiungere all'interno di `<subsystem xmlns="urn:jboss:domain:messaging-activemq:13.0">` la coda utilizzata dal progetto:
```
<jms-queue name="MessageQueue" entries="java:/jms/queue/MessageQueue"/>
```
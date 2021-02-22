Soap Call -
==========================
curl --location --request POST 'localhost:8091/soapWS' \
--header 'Content-Type: text/xml' \
--data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:us="http://techprimers.com/spring-boot-soap-example">
    <soapenv:Header/>
    <soapenv:Body>
        <us:getUserRequest>
            <us:name></us:name>
        </us:getUserRequest>
    </soapenv:Body>
</soapenv:Envelope>'



Rest Call -
=======================
curl --location --request GET 'localhost:8091/test/1'







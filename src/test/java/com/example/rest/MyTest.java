package com.example.rest;

import com.example.rest.mainService.service.Commands;
import com.example.rest.mainService.service.DataBaseCommands;
import com.example.rest.mainService.service.JsonCommands;
import com.example.rest.mainService.service.ReadFromFileLinks;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class MyTest {
    private JsonCommands jsonCommands = new Commands();
    private DataBaseCommands dataBaseCommands = new Commands();

    @Test
    public void testUrlConnectionTrue() throws IOException {
        String url = "http://www.google.com";
        assertEquals(url,jsonCommands.getUrlConnect(url).toString());
    }

    @Test(expected = Exception.class)
    public void testUrlConnectionFalse() throws IOException {
        String url = "http://wwww.google.com";
        jsonCommands.getUrlConnect(url);
    }

    @Test
    public void testDataThroughUrlTrue() throws IOException {
        String inputUrl = "https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/23567e24f52746ef92c470be6059d193/documents";
        String expected = "{\"data\": [{\"hash\": \"md5:232dba893a22ac722249ad92f8bccf22\", \"format\": \"text/plain\", \"url\": \"https://public-docs-sandbox.prozorro.gov.ua/get/3500487074064bd98f1076c21fe69e9a?KeyID=1331dc52&Signature=w%252BTQLJCiU%2FDQXfp%2FxB0VfDNRzImPv7zch3e8H1jfVOZrDJZuam%2FOTVLlvpdUiz9WVLHdUzdMrQJclbl4Vs28CQ%253D%253D\", \"title\": \"11.09.2018.xlsx\", \"documentOf\": \"tender\", \"datePublished\": \"2018-09-19T13:12:21.136232+03:00\", \"documentType\": \"subContract\", \"dateModified\": \"2018-09-19T13:12:21.136263+03:00\", \"id\": \"4f6d6dc59d1844bb80143ccc2e727a2f\"}, {\"hash\": \"md5:ee80acf16c48f3b659a2132526ae9800\", \"format\": \"application/pkcs7-signature\", \"url\": \"https://public-docs-sandbox.prozorro.gov.ua/get/04a93d0ba3e44e7e93a104a1b1ec8aaf?KeyID=1331dc52&Signature=o1V0G3cmFYjuu7MqIJxTY9zhDAj7PTblieehL0PEG%2FA5uc0VZlpY%252BMhQy0ZxTPdymnkPvAVxivDEodSI4RFDCw%253D%253D\", \"title\": \"sign.p7s\", \"documentOf\": \"tender\", \"datePublished\": \"2018-09-19T13:13:07.776613+03:00\", \"dateModified\": \"2018-09-19T13:13:07.776633+03:00\", \"id\": \"a5ef4c3063d94b10a13630fa9cca90b9\"}, {\"hash\": \"md5:061044f40512fa72e03c2674d1539e0f\", \"format\": \"text/plain\", \"url\": \"https://public-docs-sandbox.prozorro.gov.ua/get/5e03ef2402bd42ddb7dc78d526c95f81?KeyID=1331dc52&Signature=VjKGxj7WlIe%252BqZpE4OJl1qtIt0VQJ%2FMff8n8KaJWVHDANkFZB%252BcZbsq%2Fr%252BzW6S81cqeSU9hYlLLnq1WGpJ%252BuCQ%253D%253D\", \"title\": \"\\u0442\\u0435\\u0441\\u0442.docx\", \"documentOf\": \"change\", \"datePublished\": \"2018-09-24T16:00:29.527286+03:00\", \"dateModified\": \"2018-09-24T16:00:29.527311+03:00\", \"relatedItem\": \"6167ab1f7a184f75881b166b9c2e9193\", \"id\": \"f58353848e744791ad72f9baf84b5734\"}]}";
        URL url = jsonCommands.getUrlConnect(inputUrl);
        assertEquals(expected,jsonCommands.getDataThroughUrl(url).toString());
    }

    @Test(expected = MalformedURLException.class)
    public void testDataThroughUrlFalse() throws IOException {
        String inputUrl = "abc";
        URL url = jsonCommands.getUrlConnect(inputUrl);
        jsonCommands.getDataThroughUrl(url);
    }

    @Test
    public void testDataToJsonArrayTrue() throws ParseException {
        String expected = "[{\"datePublished\":\"2018-09-19T13:12:21.136232+03:00\",\"documentType\":\"subContract\",\"format\":\"text\\/plain\"," +
                "\"dateModified\":\"2018-09-19T13:12:21.136263+03:00\",\"id\":\"4f6d6dc59d1844bb80143ccc2e727a2f\",\"title\":\"11.09.2018.xlsx\",\"hash\":\"md5:232dba893a22ac722249ad92f8bccf22\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/3500487074064bd98f1076c21fe69e9a?KeyID=1331dc52&Signature=w%252BTQLJCiU%2FDQXfp%2FxB0VfDNRzImPv7zch3e8H1jfVOZrDJZuam%2FOTVLlvpdUiz9WVLHdUzdMrQJclbl4Vs28CQ%253D%253D\"," +
                "\"documentOf\":\"tender\"},{\"datePublished\":\"2018-09-19T13:13:07.776613+03:00\",\"format\":\"application\\/pkcs7-signature\"," +
                "\"dateModified\":\"2018-09-19T13:13:07.776633+03:00\",\"id\":\"a5ef4c3063d94b10a13630fa9cca90b9\",\"title\":\"sign.p7s\",\"hash\":\"md5:ee80acf16c48f3b659a2132526ae9800\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/04a93d0ba3e44e7e93a104a1b1ec8aaf?KeyID=1331dc52&Signature=o1V0G3cmFYjuu7MqIJxTY9zhDAj7PTblieehL0PEG%2FA5uc0VZlpY%252BMhQy0ZxTPdymnkPvAVxivDEodSI4RFDCw%253D%253D\"," +
                "\"documentOf\":\"tender\"},{\"datePublished\":\"2018-09-24T16:00:29.527286+03:00\",\"format\":\"text\\/plain\"," +
                "\"dateModified\":\"2018-09-24T16:00:29.527311+03:00\",\"id\":\"f58353848e744791ad72f9baf84b5734\",\"title\":\"тест.docx\"," +
                "\"relatedItem\":\"6167ab1f7a184f75881b166b9c2e9193\",\"hash\":\"md5:061044f40512fa72e03c2674d1539e0f\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/5e03ef2402bd42ddb7dc78d526c95f81?KeyID=1331dc52&Signature=VjKGxj7WlIe%252BqZpE4OJl1qtIt0VQJ%2FMff8n8KaJWVHDANkFZB%252BcZbsq%2Fr%252BzW6S81cqeSU9hYlLLnq1WGpJ%252BuCQ%253D%253D\"," +
                "\"documentOf\":\"change\"}]";
        StringBuilder data = new StringBuilder();
        data.append("{\"data\": [{\"hash\": \"md5:232dba893a22ac722249ad92f8bccf22\", \"format\": \"text/plain\", " +
                "\"url\": \"https://public-docs-sandbox.prozorro.gov.ua/get/3500487074064bd98f1076c21fe69e9a?KeyID=1331dc52&Signature=w%252BTQLJCiU%2FDQXfp%2FxB0VfDNRzImPv7zch3e8H1jfVOZrDJZuam%2FOTVLlvpdUiz9WVLHdUzdMrQJclbl4Vs28CQ%253D%253D\", " +
                "\"title\": \"11.09.2018.xlsx\", \"documentOf\": \"tender\", \"datePublished\": \"2018-09-19T13:12:21.136232+03:00\", " +
                "\"documentType\": \"subContract\", \"dateModified\": \"2018-09-19T13:12:21.136263+03:00\", \"id\": " +
                "\"4f6d6dc59d1844bb80143ccc2e727a2f\"}, {\"hash\": \"md5:ee80acf16c48f3b659a2132526ae9800\", \"format\": \"application/pkcs7-signature\", " +
                "\"url\": \"https://public-docs-sandbox.prozorro.gov.ua/get/04a93d0ba3e44e7e93a104a1b1ec8aaf?KeyID=1331dc52&Signature=o1V0G3cmFYjuu7MqIJxTY9zhDAj7PTblieehL0PEG%2FA5uc0VZlpY%252BMhQy0ZxTPdymnkPvAVxivDEodSI4RFDCw%253D%253D\", " +
                "\"title\": \"sign.p7s\", \"documentOf\": \"tender\", \"datePublished\": \"2018-09-19T13:13:07.776613+03:00\", \"dateModified\": \"2018-09-19T13:13:07.776633+03:00\", " +
                "\"id\": \"a5ef4c3063d94b10a13630fa9cca90b9\"}, {\"hash\": \"md5:061044f40512fa72e03c2674d1539e0f\", \"format\": \"text/plain\", " +
                "\"url\": \"https://public-docs-sandbox.prozorro.gov.ua/get/5e03ef2402bd42ddb7dc78d526c95f81?KeyID=1331dc52&Signature=VjKGxj7WlIe%252BqZpE4OJl1qtIt0VQJ%2FMff8n8KaJWVHDANkFZB%252BcZbsq%2Fr%252BzW6S81cqeSU9hYlLLnq1WGpJ%252BuCQ%253D%253D\", " +
                "\"title\": \"\\u0442\\u0435\\u0441\\u0442.docx\", \"documentOf\": \"change\", \"datePublished\": \"2018-09-24T16:00:29.527286+03:00\", " +
                "\"dateModified\": \"2018-09-24T16:00:29.527311+03:00\", \"relatedItem\": \"6167ab1f7a184f75881b166b9c2e9193\", \"id\": \"f58353848e744791ad72f9baf84b5734\"}]}");
        assertEquals(expected,jsonCommands.setDataToJsonArray(data,"data").toString());
    }

    @Test(expected = ParseException.class)
    public void testDataToJsonArrayFalse() throws ParseException {
        StringBuilder data = new StringBuilder();
        data.append("abc");
        jsonCommands.setDataToJsonArray(data,"data");
    }

    @Test
    public void testDataFromDatabaseByHashTrue() throws IOException {
        String hash = "md5:232dba893a22ac722249ad92f8bccf22";
        List actual = dataBaseCommands.getDataFromDatabaseByHash(hash);
        String expected = "[{\"datePublished\":\"2018-09-19T13:12:21.136232+03:00\",\"documentType\":\"subContract\"," +
                "\"format\":\"text\\/plain\",\"dateModified\":\"2018-09-19T13:12:21.136263+03:00\",\"id\":\"4f6d6dc59d1844bb80143ccc2e727a2f\"," +
                "\"title\":\"11.09.2018.xlsx\",\"hash\":\"md5:232dba893a22ac722249ad92f8bccf22\",\"" +
                "url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/3500487074064bd98f1076c21fe69e9a?KeyID=1331dc52&Signature=w%252BTQLJCiU%2FDQXfp%2FxB0VfDNRzImPv7zch3e8H1jfVOZrDJZuam%2FOTVLlvpdUiz9WVLHdUzdMrQJclbl4Vs28CQ%253D%253D\"," +
                "\"documentOf\":\"tender\"}]";
        assertEquals(expected, actual.toString());
    }

    @Test
    public void testDataFromDatabaseByHashNotFound() throws IOException {
        String hash = "abc";
        List actual = dataBaseCommands.getDataFromDatabaseByHash(hash);
        String expected = "[]";
        assertEquals(expected, actual.toString());
    }

    @Test
    public void testDataFromDatabase() throws IOException {
        String expected = "[{\"datePublished\":\"2018-10-08T15:13:06.456131+03:00\",\"documentType\":\"contractSigned\"," +
                "\"format\":\"application\\/vnd.openxmlformats-officedocument.spreadsheetml.sheet\",\"description\":\"\"," +
                "\"dateModified\":\"2018-10-08T15:13:06.456152+03:00\",\"id\":\"c973e26efa78408c8cf7adbb3c2b11e5\"," +
                "\"title\":\"Додаткова угода.xlsx\",\"hash\":\"md5:787caaf33e54d10e6cd302bce098564c\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/69966a5cbdf94ea9a7420ade7d97ca56?KeyID=1331dc52&Signature=7feOocmK9uCwci0%2FrHMXl4jScyfOTK7XQjnRsJLIZFN21Z9hRDjeZJrrVn4XXiqGjgOSQ6XKtTggvJHe6G8cDQ%253D%253D\"," +
                "\"documentOf\":\"tender\"}, {\"datePublished\":\"2018-10-17T11:53:01.587843+03:00\",\"documentType\":\"contractAnnexe\"," +
                "\"format\":\"application\\/vnd.openxmlformats-officedocument.wordprocessingml.document\"," +
                "\"dateModified\":\"2018-10-17T11:53:01.587874+03:00\",\"id\":\"3b6a91e9c7154f6ebf5e8a8d5c11a357\"," +
                "\"title\":\"Перелік змін.docx\",\"relatedItem\":\"028162fdf8634010ac1296b11e685602\",\"hash\":\"md5:ba8ca1580920293ebb33a1dfdb4b74fe\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/766cc889cc2343fbb7ce802022cabd9b?KeyID=1331dc52&Signature=UuYK9aRGFVDCJdIXNkZnbYPvrnlmSVkLZZmJ2l%252BII7Uc1btir26W0wbT4qFe4CpajDb81%252BI5kYZ5cOQ92uJlDw%253D%253D\"," +
                "\"documentOf\":\"change\"}, {\"datePublished\":\"2018-10-17T11:54:16.657833+03:00\",\"format\":\"application\\/pkcs7-signature\"," +
                "\"dateModified\":\"2018-10-17T11:54:16.657854+03:00\",\"id\":\"f99bb2ce17c144f3a28f665a9206ab92\",\"title\":\"sign.p7s\",\"hash\":\"md5:fc4a6b43f2162a74d3de4905f6939d11\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/77dab55ea98f401688397fa6faa449ce?KeyID=1331dc52&Signature=yTE1DjbaU2dmrWrFgKwRiuj%2FF8SNosqRRoPZnaNNXmBRHOAp%2FyqITRzlCSEV3bpipWBZns%252BzBso%252BMoZBIC1ZAw%253D%253D\"," +
                "\"documentOf\":\"contract\"}, {\"datePublished\":\"2018-10-17T15:56:22.886361+03:00\",\"documentType\":\"contractAnnexe\"," +
                "\"format\":\"application\\/vnd.openxmlformats-officedocument.wordprocessingml.document\",\"dateModified\":\"2018-10-17T15:56:22.886383+03:00\"," +
                "\"id\":\"e7bf347964704ae98efba0d04b5232bb\",\"title\":\"Перелік змін.docx\",\"relatedItem\":\"eec9a01f0c3f4884a3edb5bcf9c66b8f\",\"hash\":\"md5:ba8ca1580920293ebb33a1dfdb4b74fe\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/d89e4e84fee241ebbbed07a6728dd9c7?KeyID=1331dc52&Signature=qDPX1LbM41tEWl3%2FDgpm6u%2F806x2qb6jddF68KRwbFNbz1S0I62vsiD8Q%252B5dfTEkqY%252BgYZJM%2FrpDsQNI90qWDg%253D%253D\"," +
                "\"documentOf\":\"change\"}, {\"datePublished\":\"2018-09-19T13:12:21.136232+03:00\",\"documentType\":\"subContract\"," +
                "\"format\":\"text\\/plain\",\"dateModified\":\"2018-09-19T13:12:21.136263+03:00\",\"id\":\"4f6d6dc59d1844bb80143ccc2e727a2f\"," +
                "\"title\":\"11.09.2018.xlsx\",\"hash\":\"md5:232dba893a22ac722249ad92f8bccf22\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/3500487074064bd98f1076c21fe69e9a?KeyID=1331dc52&Signature=w%252BTQLJCiU%2FDQXfp%2FxB0VfDNRzImPv7zch3e8H1jfVOZrDJZuam%2FOTVLlvpdUiz9WVLHdUzdMrQJclbl4Vs28CQ%253D%253D\"," +
                "\"documentOf\":\"tender\"}, {\"datePublished\":\"2018-09-19T13:13:07.776613+03:00\",\"format\":\"application\\/pkcs7-signature\"," +
                "\"dateModified\":\"2018-09-19T13:13:07.776633+03:00\",\"id\":\"a5ef4c3063d94b10a13630fa9cca90b9\",\"title\":\"sign.p7s\",\"hash\":\"md5:ee80acf16c48f3b659a2132526ae9800\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/04a93d0ba3e44e7e93a104a1b1ec8aaf?KeyID=1331dc52&Signature=o1V0G3cmFYjuu7MqIJxTY9zhDAj7PTblieehL0PEG%2FA5uc0VZlpY%252BMhQy0ZxTPdymnkPvAVxivDEodSI4RFDCw%253D%253D\"," +
                "\"documentOf\":\"tender\"}, {\"datePublished\":\"2018-09-24T16:00:29.527286+03:00\",\"format\":\"text\\/plain\"," +
                "\"dateModified\":\"2018-09-24T16:00:29.527311+03:00\",\"id\":\"f58353848e744791ad72f9baf84b5734\",\"title\":\"тест.docx\"," +
                "\"relatedItem\":\"6167ab1f7a184f75881b166b9c2e9193\",\"hash\":\"md5:061044f40512fa72e03c2674d1539e0f\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/5e03ef2402bd42ddb7dc78d526c95f81?KeyID=1331dc52&Signature=VjKGxj7WlIe%252BqZpE4OJl1qtIt0VQJ%2FMff8n8KaJWVHDANkFZB%252BcZbsq%2Fr%252BzW6S81cqeSU9hYlLLnq1WGpJ%252BuCQ%253D%253D\"," +
                "\"documentOf\":\"change\"}, {\"datePublished\":\"2018-11-15T10:46:48.083543+02:00\",\"format\":\"image\\/jpeg\",\"dateModified\":\"2018-11-15T10:46:48.083564+02:00\"," +
                "\"id\":\"219c45e36e4548f4963a484642420478\",\"title\":\"19WII9x.jpg\",\"hash\":\"md5:aa8f3e480ef09f68a3ba29d38526a54d\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/f104698a64f64bdf8142f7e11df74dd2?KeyID=1331dc52&Signature=oi3cRoqISdyAb4f0wEM%252B43P8Mn1eh19YH%2FGwQkoHWlLDnQWWwFhZeXGvZStKoaJ8yDh%252B5JRy%252BTm4UNyzetDoCQ%253D%253D\"," +
                "\"documentOf\":\"tender\"}, {\"datePublished\":\"2018-11-15T10:56:40.740846+02:00\",\"documentType\":\"contractAnnexe\"," +
                "\"format\":\"image\\/png\",\"dateModified\":\"2018-11-15T10:56:40.740870+02:00\",\"id\":\"b3f936cd9e214387881aecdbdcc1aeff\"," +
                "\"title\":\"zak_fb.png\",\"relatedItem\":\"a45ec75e09b7461aaa7218d66b23f018\",\"hash\":\"md5:dcc8b429838b29c8c6bbbd565b1f9b45\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/010690c6dba24402851d4e40fea90eb5?KeyID=1331dc52&Signature=RT7pybVmIlXm3QA2yOTbFyxtKtUzdeyemepwOAgbtqSeHz66x2dlhx%2FVnUX83dIo6leJzmhCJsUNVldMkwjMAw%253D%253D\"," +
                "\"documentOf\":\"change\"}, {\"datePublished\":\"2018-11-15T10:46:48.083543+02:00\",\"format\":\"image\\/jpeg\",\"dateModified\":\"2018-11-15T10:46:48.083564+02:00\"," +
                "\"id\":\"219c45e36e4548f4963a484642420478\",\"title\":\"19WII9x.jpg\",\"hash\":\"md5:aa8f3e480ef09f68a3ba29d38526a54d\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/f104698a64f64bdf8142f7e11df74dd2?KeyID=1331dc52&Signature=oi3cRoqISdyAb4f0wEM%252B43P8Mn1eh19YH%2FGwQkoHWlLDnQWWwFhZeXGvZStKoaJ8yDh%252B5JRy%252BTm4UNyzetDoCQ%253D%253D\"," +
                "\"documentOf\":\"tender\"}, {\"datePublished\":\"2018-11-15T11:22:51.416943+02:00\",\"format\":\"image\\/jpeg\",\"language\":\"uk\"," +
                "\"dateModified\":\"2018-11-15T11:22:51.416995+02:00\",\"id\":\"44c098109cd2496880d7943e3921d80f\",\"title\":\"19WII9x.jpg\",\"hash\":\"md5:aa8f3e480ef09f68a3ba29d38526a54d\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/5bdfd8cefd634da988084378da57f306?KeyID=1331dc52&Signature=ak8O%2FooCoP2yTootrSk1kLamJAmhd%252BUCPHIvh6VrMGZPnk7n3gn0pygPvCs1mK9kTYEd93ubaDiEyHQbzpNIBw%253D%253D\"," +
                "\"documentOf\":\"contract\"}, {\"datePublished\":\"2018-11-15T10:56:40.740846+02:00\",\"documentType\":\"contractAnnexe\",\"format\":\"image\\/png\"," +
                "\"dateModified\":\"2018-11-15T10:56:40.740870+02:00\",\"id\":\"b3f936cd9e214387881aecdbdcc1aeff\",\"title\":\"zak_fb.png\"," +
                "\"relatedItem\":\"a45ec75e09b7461aaa7218d66b23f018\",\"hash\":\"md5:dcc8b429838b29c8c6bbbd565b1f9b45\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/010690c6dba24402851d4e40fea90eb5?KeyID=1331dc52&Signature=RT7pybVmIlXm3QA2yOTbFyxtKtUzdeyemepwOAgbtqSeHz66x2dlhx%2FVnUX83dIo6leJzmhCJsUNVldMkwjMAw%253D%253D\"," +
                "\"documentOf\":\"change\"}, {\"datePublished\":\"2018-11-15T11:22:51.416943+02:00\",\"format\":\"image\\/jpeg\",\"language\":\"uk\"," +
                "\"dateModified\":\"2018-11-15T11:22:51.416995+02:00\",\"id\":\"44c098109cd2496880d7943e3921d80f\",\"title\":\"19WII9x.jpg\",\"hash\":\"md5:aa8f3e480ef09f68a3ba29d38526a54d\"," +
                "\"url\":\"https:\\/\\/public-docs-sandbox.prozorro.gov.ua\\/get\\/5bdfd8cefd634da988084378da57f306?KeyID=1331dc52&Signature=ak8O%2FooCoP2yTootrSk1kLamJAmhd%252BUCPHIvh6VrMGZPnk7n3gn0pygPvCs1mK9kTYEd93ubaDiEyHQbzpNIBw%253D%253D\"," +
                "\"documentOf\":\"contract\"}]";
        List actual = dataBaseCommands.getDataFromDatabase();
        assertEquals(expected, actual.toString());
    }

    @Test
    public void addDataToDatabaseTrue() throws IOException {
        DataBaseCommands test = mock(Commands.class);
        doNothing().when(test).addDataToDatabase(isA(String.class), isA(String.class));
        test.addDataToDatabase("a","a");
        verify(test, times(1)).addDataToDatabase("a","a");
    }

    @Test(expected = Exception.class)
    public void addDataToDatabaseFalse() throws IOException {
        DataBaseCommands test = mock(Commands.class);
        doThrow().when(test).addDataToDatabase(isA(String.class),isA(String.class));
        test.addDataToDatabase("a","a");
    }

    @Test
    public void setToDatabaseTrue() throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("a","aa");
        JSONArray ja = new JSONArray();
        ja.add(jo);

        JsonCommands test = mock(Commands.class);
        doNothing().when(test).setToDatabase(isA(JSONArray.class));
        test.setToDatabase(ja);
        verify(test, times(1)).setToDatabase(ja);
    }

    @Test
    public void testReadFromFile() throws IOException {
        String expected = "[https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/23567e24f52746ef92c470be6059d193/documents, " +
                "https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/4805f381d48948b1b34d6ea2daa029a3/documents, " +
                "https://lb-api-sandbox.prozorro.gov.ua/api/2.4/contracts/47fa8764e1b74f4db58f84c9db460566/documents]";
        ReadFromFileLinks readFromFileLinks = new ReadFromFileLinks();
        List actual = readFromFileLinks.getLinks();
        assertEquals(expected,actual.toString());
    }

    @Test(expected = Exception.class)
    public void testReadFromFileFalse() throws IOException {
        ReadFromFileLinks readFromFileLinks = mock(ReadFromFileLinks.class);
        doThrow(new IOException()).when(readFromFileLinks).getLinks();
        readFromFileLinks.getLinks();
    }

    @Test
    public void main() {
        Main.main(new String[] {});
    }
}
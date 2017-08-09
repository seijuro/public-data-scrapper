package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.InputType;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RecallAPIResponseParserTest {
    /**
     * testing <code>RecallAPIResponsePaser</code>
     *
     * @return
     */
    @Test
    public void testRecallAPIResponsePaser() {
        try {
            String testSample = "[{\"model\":\"Acrobats\",\"idx\":\"44\",\"signDate\":\"6/11/12 15:38\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048579},\"trademark\":\"Trousselier\",\"recallNationType\":\"2\",\"serialNumber\":\"Ref.: S 95020 Batch: 6-8-11\",\"recallType\":\"자발적리콜\",\"companyName\":\"회사정보없음\",\"productName\":\"뮤직박스완구\"},{\"model\":\"EN-EL15\",\"idx\":\"45\",\"signDate\":\"6/11/12 15:38\",\"makingNation\":\"\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048580},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"201XXXXXEXXXXX,201XXXXXFXXXXX\",\"recallType\":\"자발적리콜\",\"companyName\":\"주식회사니콘\",\"productName\":\"Li-ion 리차지어블 배터리\"},{\"model\":\"Toy trumpet \",\"idx\":\"46\",\"signDate\":\"6/11/12 15:44\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048581},\"trademark\":\"Fantastiko\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"명령에따른리콜\",\"companyName\":\"회사정보없음\",\"productName\":\"트럼펫완구\"},{\"model\":\"F-YHA100\",\"idx\":\"47\",\"signDate\":\"6/11/12 15:49\",\"makingNation\":\"국외>아시아지역>일본\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048582},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"T4984824662186, T4984824670969\",\"recallType\":\"자발적리콜\",\"companyName\":\"파나소닉에코시스템즈주식회사\",\"productName\":\"내셔널 하이브리드방식 제습기\"},{\"model\":\"F-YHB100\",\"idx\":\"48\",\"signDate\":\"6/11/12 15:54\",\"makingNation\":\"국외>아시아지역>일본\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048583},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"T4984824703278, T4984824713536\",\"recallType\":\"자발적리콜\",\"companyName\":\"파나소닉에코시스템즈주식회사\",\"productName\":\"내셔널 하이브리드방식 제습기\"},{\"model\":\"JM1000/1001/1002/1003\",\"idx\":\"49\",\"signDate\":\"6/11/12 15:58\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048584},\"trademark\":\"Jian Qi\",\"recallNationType\":\"2\",\"serialNumber\":\"Ref. JM1000/1001/1002/1003\",\"recallType\":\"명령에따른리콜\",\"companyName\":\"회사정보없음\",\"productName\":\"유아용 드레스\"},{\"model\":\"HYT-029FT\",\"idx\":\"50\",\"signDate\":\"6/11/12 15:59\",\"makingNation\":\"국외>그 밖의 지역>기타\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048585},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"자발적리콜\",\"companyName\":\"주식회사컴포짓\",\"productName\":\"전기스토브(오일히터)\"},{\"model\":\"GT사 제조 아발란셰 4.0 2012년 모델, 프론트 호크 SRSUNTOUR　SF11-XCM V３PM DS 26\",\"idx\":\"52\",\"signDate\":\"6/11/12 16:21\",\"makingNation\":\"\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048586},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"No. CK110301~CK110731\",\"recallType\":\"자발적리콜\",\"companyName\":\"라이트웨이프로덕츠재팬주식회사\",\"productName\":\"자전거(마운틴바이크)\"},{\"model\":\"LF228-B1, SLF2\",\"idx\":\"53\",\"signDate\":\"6/11/12 17:16\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048587},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"자발적리콜\",\"companyName\":\"MilestoneAVTechnologiesLLC\",\"productName\":\"벽걸이형 평면 스크린 TV 설치대\"},{\"model\":\" \",\"idx\":\"54\",\"signDate\":\"6/11/12 17:25\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048588},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"자발적리콜\",\"companyName\":\"JideTradingInc.\",\"productName\":\"완구 (피규어)\"}]";

            RecallAPIResponseParser parser = new RecallAPIResponseParser();
            parser.parse(InputType.TEXT, testSample);
            PublicDataAPIResult result = parser.getResult();

            if (result instanceof PublicDataAPIErrorResult) {
            }

            assertTrue(true);

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    /**
     * testing <code>testRecallAPIPageableResponsePaser</code>
     *
     * @return
     */
    @Test
    public void testRecallAPIPageableResponsePaser() {
        try {
            String testSample = "{\"number\":0,\"size\":10,\"totalPages\":849,\"numberOfElements\":10,\"totalElements\":8486,\"hasPreviousPage\":false,\"isFirstPage\":true,\"hasNextPage\":true,\"isLastPage\":false,\"hasContent\":true,\"beginPage\":0,\"endPage\":9,\"previousPage\":-1,\"nextPage\":10,\"content\":[{\"model\":\"Acrobats\",\"idx\":\"44\",\"signDate\":\"6/11/12 15:38\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048579},\"trademark\":\"Trousselier\",\"recallNationType\":\"2\",\"serialNumber\":\"Ref.: S 95020 Batch: 6-8-11\",\"recallType\":\"자발적리콜\",\"companyName\":\"회사정보없음\",\"productName\":\"뮤직박스완구\"},{\"model\":\"EN-EL15\",\"idx\":\"45\",\"signDate\":\"6/11/12 15:38\",\"makingNation\":\"\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048580},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"201XXXXXEXXXXX,201XXXXXFXXXXX\",\"recallType\":\"자발적리콜\",\"companyName\":\"주식회사니콘\",\"productName\":\"Li-ion 리차지어블 배터리\"},{\"model\":\"Toy trumpet \",\"idx\":\"46\",\"signDate\":\"6/11/12 15:44\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048581},\"trademark\":\"Fantastiko\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"명령에따른리콜\",\"companyName\":\"회사정보없음\",\"productName\":\"트럼펫완구\"},{\"model\":\"F-YHA100\",\"idx\":\"47\",\"signDate\":\"6/11/12 15:49\",\"makingNation\":\"국외>아시아지역>일본\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048582},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"T4984824662186, T4984824670969\",\"recallType\":\"자발적리콜\",\"companyName\":\"파나소닉에코시스템즈주식회사\",\"productName\":\"내셔널 하이브리드방식 제습기\"},{\"model\":\"F-YHB100\",\"idx\":\"48\",\"signDate\":\"6/11/12 15:54\",\"makingNation\":\"국외>아시아지역>일본\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048583},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"T4984824703278, T4984824713536\",\"recallType\":\"자발적리콜\",\"companyName\":\"파나소닉에코시스템즈주식회사\",\"productName\":\"내셔널 하이브리드방식 제습기\"},{\"model\":\"JM1000/1001/1002/1003\",\"idx\":\"49\",\"signDate\":\"6/11/12 15:58\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048584},\"trademark\":\"Jian Qi\",\"recallNationType\":\"2\",\"serialNumber\":\"Ref. JM1000/1001/1002/1003\",\"recallType\":\"명령에따른리콜\",\"companyName\":\"회사정보없음\",\"productName\":\"유아용 드레스\"},{\"model\":\"HYT-029FT\",\"idx\":\"50\",\"signDate\":\"6/11/12 15:59\",\"makingNation\":\"국외>그 밖의 지역>기타\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048585},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"자발적리콜\",\"companyName\":\"주식회사컴포짓\",\"productName\":\"전기스토브(오일히터)\"},{\"model\":\"GT사 제조 아발란셰 4.0 2012년 모델, 프론트 호크 SRSUNTOUR　SF11-XCM V３PM DS 26\",\"idx\":\"52\",\"signDate\":\"6/11/12 16:21\",\"makingNation\":\"\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048586},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"No. CK110301~CK110731\",\"recallType\":\"자발적리콜\",\"companyName\":\"라이트웨이프로덕츠재팬주식회사\",\"productName\":\"자전거(마운틴바이크)\"},{\"model\":\"LF228-B1, SLF2\",\"idx\":\"53\",\"signDate\":\"6/11/12 17:16\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048587},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"자발적리콜\",\"companyName\":\"MilestoneAVTechnologiesLLC\",\"productName\":\"벽걸이형 평면 스크린 TV 설치대\"},{\"model\":\" \",\"idx\":\"54\",\"signDate\":\"6/11/12 17:25\",\"makingNation\":\"국외>아시아지역>중국\",\"_id\":{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048588},\"trademark\":\"\",\"recallNationType\":\"2\",\"serialNumber\":\"\",\"recallType\":\"자발적리콜\",\"companyName\":\"JideTradingInc.\",\"productName\":\"완구 (피규어)\"}],\"sort\":null,\"status\":0,\"pageNumber\":0,\"pageSize\":10,\"firstPage\":true,\"lastPage\":false}";

            RecallAPIResponseParser parser = new RecallAPIPageableResponseParser();
            parser.parse(InputType.TEXT, testSample);
            PublicDataAPIResult result = parser.getResult();

            if (result instanceof PublicDataAPIErrorResult) {
            }

            assertTrue(true);

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }
}
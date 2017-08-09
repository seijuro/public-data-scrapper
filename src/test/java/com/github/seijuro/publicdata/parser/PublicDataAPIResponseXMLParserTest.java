package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.InputType;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublicDataAPIResponseXMLParserTest {
    @Test
    public void testParsingTest() {
        final String sampleText1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE.</resultMsg></header><body><items><item><bzowrRgstNo>124815****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34493789</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>델텍소프트웨어(주)</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 신원로283번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>135854****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34699568</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>세무법인신원</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 중부대로</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>730810****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34788828</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>에이엔피주식회사(ANPCo.Ltd)</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 권광로260번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>233880****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34762069</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>아이에이치코퍼레이션(주)</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 매탄로108번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>135827****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34719929</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>수원남부경찰서복지위원회</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 매봉로</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>135818****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34736046</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>신성산업전기주식회사</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 매탄로37번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>124814****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34360131</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>골드건설(주)</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 매탄로160번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>124811****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34341296</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>경기진흥주택관리(주)/원천성일아파트</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 동탄원천로1109번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>124811****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34341317</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>경기진흥주택관리(주)매탄동남빌라관리소</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 동수원로</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item><item><bzowrRgstNo>124811****</bzowrRgstNo><dataCrtYm>201706</dataCrtYm><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><seq>34341351</seq><wkplJnngStcd>가</wkplJnngStcd><wkplNm>경기진흥주택관리(주)원천1주공@관리소</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 매영로</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item></items><numOfRows>10</numOfRows><pageNo>1</pageNo><totalCount>6195</totalCount></body></response>";
        final String sampleText2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE.</resultMsg></header><body><item><adptDt>20060601</adptDt><bzowrRgstNo>135818****</bzowrRgstNo><crrmmNtcAmt>1060820</crrmmNtcAmt><jnngpCnt>3</jnngpCnt><ldongAddrMgplDgCd>41</ldongAddrMgplDgCd><ldongAddrMgplSgguCd>117</ldongAddrMgplSgguCd><ldongAddrMgplSgguEmdCd>101</ldongAddrMgplSgguEmdCd><scsnDt>00010101</scsnDt><vldtVlKrnNm>도매 / 전선케이블,전기,전자부품</vldtVlKrnNm><wkplIntpCd>515070</wkplIntpCd><wkplJnngStcd>가</wkplJnngStcd><wkplNm>신성산업전기주식회사</wkplNm><wkplRoadNmDtlAddr>경기도 수원시 영통구 매탄로37번길</wkplRoadNmDtlAddr><wkplStylDvcd>1</wkplStylDvcd></item></body></response>";

        PublicDataAPIResponseXMLParser parser = new PublicDataAPIResponseXMLParser();

        {
            parser.parse(InputType.TEXT, sampleText1);

            PublicDataAPIResult result = parser.getResult();
            assertNotNull(result);
            assertFalse(result instanceof PublicDataAPIErrorResult);

            assertEquals("00", result.getResultCode());
            assertEquals("NORMAL SERVICE.", result.getResultMessage());
            assertEquals(new Integer(10), result.getNumberOfRows());
            assertEquals(new Integer(1), result.getPageNo());
            assertEquals(new Integer(6195), result.getTotalCount());
        }

        {
            parser.parse(InputType.TEXT, sampleText2);
            assertFalse(parser.hasError());

            PublicDataAPIResult result = parser.getResult();
            assertNotNull(result);
            assertFalse(result instanceof PublicDataAPIErrorResult);

            assertEquals("00", result.getResultCode());
            assertEquals("NORMAL SERVICE.", result.getResultMessage());
            assertNull(result.getNumberOfRows());
            assertNull(result.getPageNo());
            assertNull(result.getTotalCount());
        }
    }
}

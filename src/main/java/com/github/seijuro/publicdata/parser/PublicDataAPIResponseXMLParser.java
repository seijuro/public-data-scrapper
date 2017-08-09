package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.InputType;
import com.github.seijuro.common.xml.parser.XMLSAXParser;
import com.github.seijuro.publicdata.property.PublicDataPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import org.xml.sax.SAXException;

@ToString
public class PublicDataAPIResponseXMLParser extends XMLSAXParser implements PublicDataAPIResponseParser {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PROTECTED)
    private String resultCode = null;
    @Getter(AccessLevel.PROTECTED)
    private String resultMessage = null;
    @Getter(AccessLevel.PROTECTED)
    private Integer numberOfRows = null;
    @Getter(AccessLevel.PROTECTED)
    private Integer pageNo = null;
    @Getter(AccessLevel.PROTECTED)
    private Integer totalCount = null;

    //  error
    private boolean hasError = false;
    @Getter(AccessLevel.PROTECTED)
    private String errorMsg = null;
    @Getter(AccessLevel.PROTECTED)
    private String authMsg = null;
    @Getter(AccessLevel.PROTECTED)
    private String reasonCode = null;

    //  final result
    private PublicDataAPIResult result = null;

    /**
     * C'tor
     *
     */
    public PublicDataAPIResponseXMLParser() {
        super();
    }

    @Override
    public void clear() {
        super.clear();

        if (this.result != null) this.result.clear();

        this.resultCode = null;
        this.resultMessage = null;
        this.numberOfRows = null;
        this.totalCount = null;
        this.pageNo = null;

        this.hasError = false;
        this.errorMsg = null;
        this.authMsg = null;
        this.reasonCode = null;

        this.result = null;
    }

    @Override
    public void parse(InputType type, String input) {
        super.parse(type, input);
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (PublicDataPropertyUtils.Result.contains(tag)) {
            int code = PublicDataPropertyUtils.Result.getCode(tag, Integer.MIN_VALUE);

            assert (code != Integer.MIN_VALUE);

            return true;
        }
        else if (PublicDataPropertyUtils.Error.contains(tag)) {
            int code = PublicDataPropertyUtils.Error.getCode(tag, Integer.MIN_VALUE);

            assert (code != Integer.MIN_VALUE);

            return true;
        }

        return false;
    }

    @Override
    protected boolean handleTagEnd(String tag, String value) {
        //  tags related to 'result'
        if (PublicDataPropertyUtils.Result.contains(tag)) {
            int code = PublicDataPropertyUtils.Result.getCode(tag, Integer.MIN_VALUE);

            assert (code != Integer.MIN_VALUE);

            switch (code) {
                case PublicDataProperty.ResultCode.RC_NUM_OF_ROWS:
                    this.numberOfRows = new Integer(value);
                    return true;
                case PublicDataProperty.ResultCode.RC_PAGE_NO:
                    this.pageNo = new Integer(value);
                    return true;
                case PublicDataProperty.ResultCode.RC_TOTAL_COUNT:
                    this.totalCount = new Integer(value);
                    return true;
                case PublicDataProperty.ResultCode.RC_RESULT_CODE:
                    this.resultCode = value;
                    return true;
                case PublicDataProperty.ResultCode.RC_RESULT_MESSAGE:
                    this.resultMessage = value;
                    return true;

                default:
                    break;
            }
        }
        //  tags related to 'error'
        else if (PublicDataPropertyUtils.Error.contains(tag)) {
            int code = PublicDataPropertyUtils.Error.getCode(tag, Integer.MIN_VALUE);

            assert (code != Integer.MIN_VALUE);

            this.hasError = true;

            switch (code) {
                case PublicDataProperty.ErrorCode.ERROR_MESSAGE:
                    this.errorMsg = value;
                    return true;
                case PublicDataProperty.ErrorCode.REASON_CODE:
                    this.reasonCode = value;
                    return true;
                case PublicDataProperty.ErrorCode.AUTHENTICATION_MESSAGE:
                    this.authMsg = value;
                    return true;

                default:
                    break;
            }
        }

        return false;
    }

    /**
     * endDocument
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

        //  default impl.
        this.result = createResult();
    }

    /**
     * create result
     * @return
     */
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            return new PublicDataAPIResult(getResultCode(), getResultMessage(), getPageNo(), getNumberOfRows(), getTotalCount());
        }

        return new PublicDataAPIErrorResult(getReasonCode(), getErrorMsg(), getAuthMsg());
    }

    @Override
    public PublicDataAPIResult getResult() {
        return this.result;
    }

    @Override
    public boolean hasError() {
        return this.hasError;
    }
}

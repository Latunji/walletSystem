//package com.osm.wallet.api.constants;
//
//
//public interface IConstants {
//    long ONE_HOUR = 3600000L;
//
//
//    int AUTO_STEP_INCREASE_MONTH = 0;
//
//    double DEVELOPMENT_LEVY = 100.0D;
//
//
//    String naira = "\u20A6";
//    String SHOW_ROW = "display:''";
//    String HIDE_ROW = "display:none";
//    String EMPTY_STR = "";
//
//    double LTG_INCREASE = 1.2D;
//    String FEMALE = "F";
//    String MALE = "M";
//
//    int PROMOTION_LIMIT = 16;
//    int CHECKED = 1;
//
//
//
//
//    int SPEC_ALLOW_IND = 5;
//    double MAX_ARREARS_PERCENTAGE = 100.0D;
//
//
//    int ON = 1;
//    int OFF = 0;
//
//    int PROMOTION = 1;
//    int LOAN = 2;
//    int DEDUCTION = 3;
//
//
//    String TPS_HIRE_DATE_STR = "31/12/2007";
//    String TPS_EXP_RETIRE_DATE_STR = "01/07/2025";
//
//    String APPROVED = "Approved for Payroll";
//    String UNAPPROVED = "Unapproved for Payroll";
//    String APPROVED_BIOMETRIC = "Verified";
//    String UNAPPROVED_BIOMETRIC = "Unverified";
//   // String YES_IND = "Y";
//    //String NO_IND = "N";
//    String ALHAJA = "Alhaja";
//    String DEACONESS = "Deaconess";
//    String DR_MRS = "Dr./Mrs.";
//    String MISS = "Miss.";
//    String MS = "Ms.";
//    String MRS = "Mrs.";
//    /*public static final String TPS_AGE_START = "31/12/2007";
//    public static final String TPS_AGE_END = "01/07/2025";*/
//    String HTML_COPYRIGHT = "&copy;";
//    String REDIRECT_TO_DASHBOARD = "redirect:determineDashBoard.do";
//    String REDIRECT_TO_DELETE_PAYROLL = "redirect:deletePendingPayroll.do";
//    //String REDIRECT_TO_HR_FXN_DASHBOARD = "redirect:hrAdminHomeForm.do";
//    String REDIRECT_TO_DEPT_FXN_DASHBOARD = "redirect:departmentFunctionalities.do";
//    String REDIRECT_FUT_SIM_DASHBOARD = "redirect:preFutureSimReportForm.do";
//    int TPS_IND = 1;
//    int CPS_IND = 2;
//    int ERROR_PAYROLL_IND = 3;
//    int AWAITING_APPROVAL_IND = 2;
//    int CANCEL_PAYROLL_IND = 4;
//    Integer POL_APP_ID = 18;
//
//    Integer HOPS_ID = 19;
//    String NAMED_ENTITY = "ne";
//
//    String EMP_SKEL = "emp_skel";
//    String PS_BEAN = "ps_bean";
//
//    //Objects Moved from Base Controller.
//    int pageLength = 20;
//    String BLOCK = "block";
//
//    String IPPMS_EXCEPTION = "uncaughtException";
//    String NONE = "none";
//    String TRUE = "true";
//    String FALSE = "false";
//    String REQUEST_PARAM_ADD = "_add";
//    String REQUEST_PARAM_UPD = "_update";
//    String REQUEST_PARAM_CANCEL = "_cancel";
//    String REQUEST_PARAM_MERGE = "_merge";
//    String REQUEST_PARAM_CANCEL_VALUE = "cancel";
//    String REQUEST_PARAM_CLOSE = "_close";
//    String REQUEST_PARAM_CONFIRM = "_confirm";
//    String REQUEST_PARAM_CREATE = "_create";
//    String REQUEST_PARAM_OK = "_ok";
//    String REQUEST_PARAM_GO = "_go";
//    String REQUEST_PARAM_DEACTIVATE = "_deactivate";
//    String REQUEST_PARAM_SEARCH = "_search";
//    String REQUEST_PARAM_APPROVE = "_approve";
//    String REQUEST_PARAM_REJECT = "_reject";
//    String REQUEST_PARAM_PROMOTE = "_promote";
//    String REQUEST_PARAM_ADD_LOAN = "_addloan";
//    String REQUEST_PARAM_VERIFY = "_verify";
//    String REQUEST_PARAM_ADD_DEDUCTION = "_addDeduction";
//    String REQUEST_PARAM_TRANSFER = "_transfer";
//    String REQUEST_PARAM_TERMINATE = "_terminate";
//    String REQUEST_PARAM_ADD_SPEC_ALLOW = "_addSpecAllow";
//    String REQUEST_PARAM_EDIT = "_edit";
//    String CONFIG_HOME_URL = "redirect:configurationHome.do";
//    String REQUEST_PARAM_UPDATE = "_update";
//    String REQUEST_PARAM_DELETE = "_delete";
//    String REQUEST_PARAM_REPLACE = "_replace";
//    String REQUEST_PARAM_SIMULATE = "_simulate";
//    String REQUEST_PARAM_LOAD = "_load";
//    String REQUEST_PARAM_DONE = "_done";
//    String REQUEST_PARAM_NEXT = "_next";
//    String REQUEST_PARAM_FINISH = "_finish";
//    String REQUEST_PARAM_SCHED = "_schedule";
//    String REQUEST_PARAM_UPDATE_REPORT = "_updateReport";
//    String REQUEST_PARAM_SEND_EMAIL = "_sendEmail";
//    String REQUEST_PARAM_UPDATE_REPORT_VALUE = "updateReport";
//    String SELECTED_MONTHLY_VARAIATION_OBJECT = "schoolSelected";
//    String SCHOOL_SELECTED_MONTHLY_VARIATION = "selected";
//
//    //-- Spring Security stuffs...
//    String DETERMINE_DASHBOARD_URL = "redirect:/determineDashBoard.do";
//    String CHANGE_PASSWORD_URL = "redirect:/changePassword.do";
//
//    String SIGN_OUT_URL = "secure/securedLogoutForm";
//    String PERMISSION_DENIED_URL = "redirect:/permissionDenied.do";
//    String SAVE_PARAM = "save_op";
//    Object SAVE_PARAM_VALUE = "_save";
//    String OUTCOME_MSG_KEY = "outcomeMsg";
//    String REQUEST_PARAM_PRINT = "_print";
//    String REQUEST_PARAM_PRINT_VALUE = "print";
//   // String ORIG_FORM = "orig_form";
//    String LTG_ATTR_NAME = "ltg_holder";
//    String CHECK_REG_ATTR_NAME = "check_reg";
//  //  String GEN_PAY_ATTR = "gen_pay";
//    String MASS_DED_ATTR_NAME = "mass_ded";
//    String MASS_PAYSLIP_EMAIL_NAME = "mass_email_payslip";
//    String MASS_LOAN_ATTR_NAME = "mass_loan";
//    String MASS_PROMO_ATTR_NAME = "mass_promo";
//    String MASS_SA_ATTR_NAME = "mass_sa";
//    String MASS_TRANS_ATTR_NAME = "mass_trans";
//   // String MOVE_EMP_PAYGROUP = "move_emp";
//    String SIM_ATTR = "simul";
//    String EMP_SKEL_ERR = "emp_skel_error";
//   // String CREATE_PG = "make_pg";
//    int CURRENT_BVN_LENGTH = 11;
//    String CONFIG_BEAN = "er_config";
//    String BVN_CONFIG_BEAN = "bvn_config";
//    String CUSTOM_REPORT = "x_c_r";
//
//
//    String SAVED_INDICATOR_KEY = "saved";
//    String SAVED_MSG = "savedMsg";
//    String SAVED_URL = "savedUrl";
//    String DISPLAY_ERRORS = "displayErrors";
//
//    //String BULK_PAY_SLIPS = "xb_p_s";
//   // String RURAL_SPEC_ALLOW_TYPE = "RURAL";
//    String FU_OBJ_KEY = "_fuobj";
//
//    String MINI_SAL_KEY = "_miBin$t76_";
//
//
//    int TWO = 2;
//    String REQUEST_PARAM_CREATE_CONTRACT = "_createContract";
//
//    String FILE_UPLOAD_OBJ_IND = "_objInd";
//    String FILE_UPLOAD_UUID = "_fuUID";
//    String DEL_STEP_INC = "ippms_del_step_increment";
//    String DEL_SING_RERUN_PAYCHECKS = "ippms_del_rerun_paychecks";
//    String DEL_PAYROLL_RERUN_PROC = "ippms_del_payroll_rerun";
//    String UPD_RERUN_IND_PROC = "ippms_update_rerun_paychecks";
//    /**
//     * Do not alter this values.
//     * See com.osm.gnl.ippms.ogsg.web.ui.filter.MenuFilter
//     */
//    String DASHBOARD_URL = "/determineDashBoard.do";
//   // String DISP_MASS_ENTRY_RESULT = "/displayMassEntryResult.do";
//   // String FINALIZE_CUSTOM_REPORT = "/finalizeCustomReport.do";
// //   String UPLOAD_SUBMIT_URL = "/uploadSubmit.do";
//}
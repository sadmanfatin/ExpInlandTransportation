package mnj.expinland.view.managed_bean;

import java.sql.CallableStatement;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;    
import oracle.adf.model.BindingContext;    
//import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;    
import oracle.jbo.domain.Number;    
import oracle.binding.OperationBinding;   
import oracle.adf.model.binding.DCDataControl; 
import javax.faces.application.FacesMessage;  
import javax.faces.context.ExternalContext;  
import javax.faces.context.FacesContext;    
import javax.faces.event.ValueChangeEvent;    
import javax.servlet.http.HttpSession;

import mnj.ont.model.services.MainAMImpl;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import oracle.jbo.domain.Date;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import oracle.jbo.domain.Timestamp;

import oracle.jdbc.OracleTypes;


public class Main {


    private RichTable fillTable;
    private RichInputText cartonQuantity;
    private RichInputText cartonAssign;
    private RichInputText attributeNum10;
    private RichInputText headerOrgId;
    private RichSelectOneChoice cnFName;
    private RichOutputText orgIdTrans;
    private RichInputText orgIdBind;
    private RichInputDate cargoUnloadingBinding;
    private RichSelectOneRadio rcvflag;
    private RichCommandButton pouplateBtn;

    public Main() {
    super();
    }

    private RichTable expInlandLinesBind;

    /** Bean Coding For Export Inland Popup Tab **/
        
        public void editPopupFetchListenerExpInland(PopupFetchEvent popupFetchEvent) {
                      
                  System.out.println("sabih Error First");
                setWhereClauseExpInland();
                
                  if (popupFetchEvent.getLaunchSourceClientId().contains("cbInsert")) {
                    
                      BindingContainer bindings = getBindings();
                      OperationBinding operationBinding = 
                                              bindings.getOperationBinding("CreateInsert");
                      operationBinding.execute();
                  }
              }


    public void editPopupCancelListenerExpInland(PopupCanceledEvent popupCanceledEvent) {
               
           }

    public void setWhereClauseExpInland(){
        
        System.out.println("sabih Error Here");
        OperationBinding operationBinding = executeOperation("populateExpInlandLines1");  //("populatePiLines1");
        System.out.println("sabih Error Here2");
        
   //     System.out.println("sabih Error 1   "+getOrgId().getValue());
       // operationBinding.getParamsMap().put("OrgId", getOrgId().getValue());
     //   System.out.println("sabih Error 2   "+getOrgId().getValue());
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
           System.out.println("if errors-->");
    //            List errors = operationBinding.getErrors();
    //            System.out.println(@);
        }
    }

    public void editDialogListenerExpInland(DialogEvent dialogEvent) {
               if (dialogEvent.getOutcome().name().equals("ok")) {     
               // FillExpInland();
                   AdfFacesContext.getCurrentInstance().addPartialTarget(expInlandLinesBind);    
               } else if (dialogEvent.getOutcome().name().equals("cancel")) {          
                ;
               }
           }

    public  void FillExpInland() {
            OperationBinding operationBinding = executeOperation("callExpInlandFetch");
            operationBinding.execute();
           }
        
        
    public OperationBinding executeOperation(String operation) {          
        OperationBinding createParam = getBindingsCont().getOperationBinding(operation);          
        return createParam;        
    }

    public BindingContainer getBindings() {
               return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    /*****Generic Method to Get BindingContainer**/          
    public BindingContainer getBindingsCont() {             
        return BindingContext.getCurrent().getCurrentBindingsEntry();          }  


    public void setExpInlandLinesBind(RichTable expInlandLinesBind) {
        this.expInlandLinesBind = expInlandLinesBind;
    }

    public RichTable getExpInlandLinesBind() {
        return expInlandLinesBind;
    }
    
    /////////////invoice popup header///////////////////////////
       public void editPopupFetchListenerInvoice(PopupFetchEvent popupFetchEvent) {
//        BindingContainer bindings = getBindings();
//        OperationBinding operationBinding = bindings.getOperationBinding("selectAllLines1");
//        operationBinding.execute();
           System.out.println("editPopupFetchListenerInvoice");
       }
       
       public void editDialogListShadeBPO(DialogEvent dialogEvent) {
             
               if (dialogEvent.getOutcome().name().equals("ok")) {
                   OperationBinding operationBinding =
                       executeOperation("FillBpoHeader");
                   operationBinding.execute();
                   OperationBinding operationBinding1 =
                       executeOperation("popupDeselectActionListener");
                   operationBinding1.execute();
                   
                   
                   
                   AdfFacesContext.getCurrentInstance().addPartialTarget(fillTable);
               }

           } 
       public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {

       }
       
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////
       
//       public void valueChanger(ValueChangeEvent valueChangeEvent) {
//           // Add event code here...
//           double CartonQuantity = Double.parseDouble((getCartonQuantity().getValue().toString()));
//           double CartonAssign = Double.parseDouble((getCartonAssign().getValue().toString()));
//           double ff = 0.0,gg = 0.0, ee = 0.0,  hh = 0.0,  MWM = 0.0, NN =0.0; 
//           
//           ff = CartonQuantity - CartonAssign ;
//           
//           ViewObject linesVO = this.getLineVO1();
//                  Row row = linesVO.getCurrentRow();
//                  row.setAttribute("SampleQty", sampleQty);
//                  detailRowSet.closeRowSetIterator();
//         
//
//       }
       
       
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void setFillTable(RichTable fillTable) {
        this.fillTable = fillTable;
    }

    public RichTable getFillTable() {
        return fillTable;
    }

    public void setCartonQuantity(RichInputText cartonQuantity) {
        this.cartonQuantity = cartonQuantity;
    }

    public RichInputText getCartonQuantity() {
        return cartonQuantity;
    }

    public void setCartonAssign(RichInputText cartonAssign) {
        this.cartonAssign = cartonAssign;
    }

    public RichInputText getCartonAssign() {
        return cartonAssign;
    }

    public void popupFlagValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
       // System.out.println(valueChangeEvent.getNewValue());
    }

    public void valueChange_TrackingNO(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("valueChange_TrackingNO");
        String TrackingNum = valueChangeEvent.getNewValue().toString(); //getAttributeNum10().getValue().toString() ;
        System.out.println("TrackingNum "+TrackingNum);
        OperationBinding operationBinding1 = executeOperation("populateTracking");
        //operationBinding1.getParamsMap().put("TrackingNum", TrackingNum);
        operationBinding1.getParamsMap().put("TrackingNum", TrackingNum);
        operationBinding1.execute();
    }

    public void setAttributeNum10(RichInputText attributeNum10) {
        this.attributeNum10 = attributeNum10;
    }

    public RichInputText getAttributeNum10() {
        return attributeNum10;
    }

    public void setHeaderOrgId(RichInputText headerOrgId) {
        this.headerOrgId = headerOrgId;
    }

    public RichInputText getHeaderOrgId() {
        return headerOrgId;
    }

    public void setCnFName(RichSelectOneChoice cnFName) {
        this.cnFName = cnFName;
    }

    public RichSelectOneChoice getCnFName() {
        
        return cnFName;
    }

    public void setOrgIdTrans(RichOutputText orgIdTrans) {
        this.orgIdTrans = orgIdTrans;
    }

    public RichOutputText getOrgIdTrans() {
        return orgIdTrans;
    }

    public void setOrgIdBind(RichInputText orgIdBind) {
        this.orgIdBind = orgIdBind;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession userSession = (HttpSession) ectx.getSession(false);
        userSession.setAttribute("OrgID",orgIdBind.getValue());
    }

    public RichInputText getOrgIdBind() {
        return orgIdBind;
    }

    public String saveButton() {
        // Add event code here...
        
        
        OperationBinding operationBinding1 = executeOperation("getUnitName");
        //operationBinding1.getParamsMap().put("TrackingNum", TrackingNum);
        operationBinding1.execute();
        return null;
    }

    public void cargoHandOverDateValidation(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        OperationBinding operationBinding1 = executeOperation("cargoHandOverDateValidation");
        //operationBinding1.getParamsMap().put("TrackingNum", TrackingNum);
        operationBinding1.execute();

        

//        java.util.Date date = null; 
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try{
//            date = formatter.parse((String)valueChangeEvent.getNewValue());
//            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//            oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
//            System.out.println("Cargo Handover Date "+jboDate);
//        }catch(Exception e){
//            e.printStackTrace(); 
//        }
//        
//        java.util.Date date1 = null; 
//        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try{
//            date1 = formatter1.parse((String)getCargoUnloadingBinding().getValue());
//            java.sql.Date sqlDate1 = new java.sql.Date(date.getTime());
//            oracle.jbo.domain.Date jboDate1 = new oracle.jbo.domain.Date(sqlDate1);
//            System.out.println("Cargo Unloading Date "+jboDate1);
//        }catch(Exception e){
//            e.printStackTrace(); 
//        }
             
//          
//                oracle.jbo.domain.Date cargoUnloadingDate;
//                oracle.jbo.domain.Date cargoHandoverDate;
//                              
//               // cargoUnloadingDate=(oracle.jbo.domain.Date) getCargoUnloadingBinding().getValue();
//                cargoHandoverDate=(oracle.jbo.domain.Date) valueChangeEvent.getNewValue();
//
//                Timestamp ts1=cargoUnloadingDate.timestampValue();  
//                Timestamp ts2=cargoHandoverDate.timestampValue();
// 
//                System.out.println("Cargo Unloading Date "+ts1.getTime());
//                System.out.println("Cargo Handover Date "+ts2.getTime());
//                
//                long sec=(ts2.getTime()-ts1.getTime());
//                
//                System.out.println("Minutes are " +sec);
//    
    }

    public void setCargoUnloadingBinding(RichInputDate cargoUnloadingBinding) {
        this.cargoUnloadingBinding = cargoUnloadingBinding;
    }

    public RichInputDate getCargoUnloadingBinding() {
        return cargoUnloadingBinding;
    }

    public void commDetailSelectListener(SelectionEvent selectionEvent) {
        // Add event code here...
        makeCurrent(selectionEvent);
    }
    
    public static void makeCurrent( 
        org.apache.myfaces.trinidad.event.SelectionEvent selectionEvent){ 
        RichTable _table = (RichTable) selectionEvent.getSource(); 
        //the Collection Model is the object that provides the 
        //structured data for the table to render 
        org.apache.myfaces.trinidad.model.CollectionModel _tableModel = (org.apache.myfaces.trinidad.model.CollectionModel) _table.getValue(); 
        //the ADF object that implements the CollectionModel is  JUCtrlHierBinding. It is wrapped by the CollectionModel API 
        oracle.jbo.uicli.binding.JUCtrlHierBinding _adfTableBinding = (oracle.jbo.uicli.binding.JUCtrlHierBinding) _tableModel.getWrappedData(); 
        //Acess the ADF iterator binding that is used with ADF table binding 
        oracle.adf.model.binding.DCIteratorBinding  _tableIteratorBinding = _adfTableBinding.getDCIteratorBinding(); 
        //the role of this method is to synchronize the table component selection with the selection in the ADF model 
        Object _selectedRowData = _table.getSelectedRowData(); 
         //cast to JUCtrlHierNodeBinding, which is the ADF object that represents a row 
         oracle.jbo.uicli.binding.JUCtrlHierNodeBinding _nodeBinding = (oracle.jbo.uicli.binding.JUCtrlHierNodeBinding) _selectedRowData; 
         oracle.jbo.Key _rwKey = _nodeBinding.getRowKey(); 
         _tableIteratorBinding.setCurrentRowWithKey(_rwKey.toStringFormat(true)); 
    }

    public void cargoUnloadingDate_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        
     
        // Add event code here...
        //valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        OperationBinding operationBinding1 = executeOperation("cargoHandOverDateValidation");
        //operationBinding1.getParamsMap().put("TrackingNum", TrackingNum);
        operationBinding1.execute();
    }

    public String saveBtnAction() {
        // Add event code here...
        OperationBinding operationBinding1 = executeOperation("saveBtnAction");
        //operationBinding1.getParamsMap().put("TrackingNum", TrackingNum);
        operationBinding1.execute();
        am.getFillBpoVO1().executeQuery();
        refreshQueryKeepingCurrentRow(am.getInlandTransportLinesVO1());
        return null;
    }

    public void AssignCartonQty_ValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
//        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
//        OperationBinding operationBinding1 = executeOperation("populateBalancePerPcs");
//        operationBinding1.execute();
    }

    public String populateTrackingInfo() {
        // Add event code here...
        //valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        OperationBinding operationBinding1 = executeOperation("populateTrackingInfo");
        operationBinding1.execute();
        pouplateBtn.setDisabled(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pouplateBtn);
        return null;
    }
    
    
    
    public ApplicationModule getAppM(){
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("MainAMDataControl"); // Name of application module in datacontrolBinding.cpx
        MainAMImpl appM = (MainAMImpl)dc.getDataProvider();
        return appM;
    }
    MainAMImpl am = (MainAMImpl)this.getAppM();
    
    
    public void generateTrackingNo(ValueChangeEvent vce) {
        String flag = vce.getNewValue().toString();
        String trackingNo = new Date(new java.sql.Timestamp(System.currentTimeMillis())).toString();
        String track = trackingNo.replaceAll("-", "");
        String track2 = track.replaceAll(":", "");
        String track3 = track2.replaceAll("\\s", "");
        String track4 = track3.substring(0,14);
        System.out.println("TRACKING NO ="+track4);
        System.out.println("RCV ="+flag);
        
        if(flag.equals("1")){ 
            attributeNum10.setValue(track4);
            attributeNum10.setReadOnly(true);   
            pouplateBtn.setDisabled(false);
        }else if(flag.equals("2")){
            attributeNum10.setReadOnly(false);
            attributeNum10.setValue(null);
            pouplateBtn.setDisabled(false);
        }else{
            attributeNum10.setValue(null);
            attributeNum10.setReadOnly(true);
            pouplateBtn.setDisabled(true);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(attributeNum10); 
        AdfFacesContext.getCurrentInstance().addPartialTarget(pouplateBtn);
    }

    public void setRcvflag(RichSelectOneRadio rcvflag) {
        this.rcvflag = rcvflag;
    }

    public RichSelectOneRadio getRcvflag() {
        return rcvflag;
    }

    public void setPouplateBtn(RichCommandButton pouplateBtn) {
        this.pouplateBtn = pouplateBtn;
    }

    public RichCommandButton getPouplateBtn() {
        return pouplateBtn;
    }
    
    
    
    
    
    public void refreshQueryKeepingCurrentRow(ViewObject viewObject )  {
        
        
         Row currentRow;
         Key currentRowKey;
         
         // added on 7.jan.18 to handle exception if view object has no current row
        try{
           currentRow = viewObject.getCurrentRow();
           currentRowKey = currentRow.getKey();
        }
        catch(Exception e){
            return;
        }
        
     
       
      
       int rangePosOfCurrentRow = viewObject.getRangeIndexOf(currentRow);
       int rangeStartBeforeQuery = viewObject.getRangeStart();
       viewObject.executeQuery();
       viewObject.setRangeStart(rangeStartBeforeQuery);
       /*
        * In 10.1.2, there is this convenience method we could use
        * instead of the remaining lines of code
        *
        *  findAndSetCurrentRowByKey(currentRowKey,rangePosOfCurrentRow);
        *  
        */
       
         
       Row[] rows = viewObject.findByKey(currentRowKey, 1);
       if (rows != null && rows.length == 1)
       {
          viewObject.scrollRangeTo(rows[0], rangePosOfCurrentRow);
          viewObject.setCurrentRowAtRangeIndex(viewObject.getRangeIndexOf(rows[0]));
       }
       
               
     }


    public void updateInvoiceNo(ActionEvent actionEvent) {
        // Add event code here...
        String  headerId =am.getInlandTransportHeaderVO1().getCurrentRow().getAttribute("InlandTransHeaderId").toString();
        String messsage= null ; 
        String stmt =
            "BEGIN  APPS.EXP_INLAND_TRANS_INV_UPDATE (:1 ,:2); end;";
    
           CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
           
        try {
           
            cs.setInt(1, Integer.parseInt(headerId));
            cs.registerOutParameter(2, OracleTypes.VARCHAR);
            cs.execute();
            messsage = cs.getString(2);
            cs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if( messsage != null){
            showMessage( messsage, "error");
            return ;
        }      
        
        am.getInlandTransportLinesVO1().executeQuery();
        am.getDBTransaction().commit();
        
    }
    
    
    public  void showMessage(String messege , String severity ) {
            
            
            FacesMessage fm = new FacesMessage(messege);
            
            if(severity.equals("info")){
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
            }
            else if(severity.equals("warn")){
                fm.setSeverity(FacesMessage.SEVERITY_WARN);
            }
            else if(severity.equals("error")){
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            }
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
            
        }
    
    
    
    
    
    
    
    
    
    
    
}// Main Braces Closing


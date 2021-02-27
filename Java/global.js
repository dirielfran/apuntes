/*
 * Dado un numero float o en string devuelve el numero formateado en formato (#.###)
 */
function formatFloat(numero, formato) {
	var decimales = formato.lastIndexOf('.')+1;
	numero = numero.toString();
	decimales = decimales > 0 ? formato.length - decimales : 0;
	return numero.formatFloat(decimales);
}

/*
 * Setea el color de texto dependiendo si el valor es negativo
 */
function setNumberStyle(field) {
	if(field.value) {
		var number = field.value.toFloat();
		if(number < 0) {
			field.style.color="#FF0000";
		} else {
			field.style.color="#000000";
		}
	}
}

/*
 * Verifica si un string pasado como argumento es vacio
 */
function isEmpty(inputStr) {
	if( null == inputStr || "" == inputStr )
		return true;
	
	return false;
}

/*
 * Codifica los caracteres que pueden aparecer en un texto html para poder ser
 * utilizado como query params. Si se desea codificar algÃºn otro caracter se
 * puede obtener el código hexa de:
 * http://www.blooberry.com/indexdot/html/topics/urlencoding.htm
 */
function encodeURIComponentNew(s) {
	return s.replace(/[%]/gm, "%25")
	.replace(/[ñ]/gm, "%F1")
	.replace(/[Ñ]/gm, "%D1")
	.replace(/[°]/gm, "%B0")
	.replace(/[º]/gm, "%BA")
	.replace(/[á]/gm, "%E1")
	.replace(/[é]/gm, "%E9")
	.replace(/[í]/gm, "%ED")
	.replace(/[ó]/gm, "%F3")
	.replace(/[ú]/gm, "%FA")
	.replace(/[Á]/gm, "%C1")
	.replace(/[É]/gm, "%C9")
	.replace(/[Í]/gm, "%CD")
	.replace(/[Ó]/gm, "%D3")
	.replace(/[Ú]/gm, "%DA")
	.replace(/[&]/gm, "%26")
	;
}

function insertAtCursor(myField, myValue) {
	textAreaScrollPosition = myField.scrollTop;
	// IE support
    if (document.selection) {
        myField.focus();

        // in effect we are creating a text range with zero
        // length at the cursor location and replacing it
        // with myValue
        sel = document.selection.createRange();
        sel.text = myValue;

    // Mozilla/Firefox/Netscape 7+ support
    } else if (myField.selectionStart || myField.selectionStart == '0') {

        myField.focus();
        // Here we get the start and end points of the
        // selection. Then we create substrings up to the
        // start of the selection and from the end point
        // of the selection to the end of the field value.
        // Then we concatenate the first substring, myValue,
        // and the second substring to get the new value.
        var startPos = myField.selectionStart;
        var endPos = myField.selectionEnd;
        myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
        myField.setSelectionRange(endPos+myValue.length, endPos+myValue.length);
    } else {
        myField.value += myValue;
    }
    myField.scrollTop = textAreaScrollPosition;
}
function cursorInside(text, startPos, endPos, strStart, strEnd) {
	if(text == undefined || text == null || text == "")
		return false;
    // Tomo el texto desde el principio hasta la posicion inicial de la
	// selecciÃ³n
    var txt1 = text.substring(0, startPos);
    var strStartLastIndex = txt1.lastIndexOf(strStart);
    if(strStartLastIndex == -1)
    	return false;
    // Tomo el texto desde el el ultimo caracter de la seleccion hasta el final
    var txt2 = text.substring(endPos, text.length);
    var strEndIndex = txt2.indexOf(strEnd);
    if(strEndIndex == -1)
    	return false;
    
    return true;
}


/*
 * Pads a string left or right to a certain length. If the length of the string
 * is bigger than the value of n, the string is truncated. str is the value to
 * pad; n is the total length. Positive for right padding and negative for left
 * padding. ch is optional and denotes the pad character. It defaults to a
 * space, when str is a string. When str is a number, ch defaults to a zero.
 */
function pad(str,n,ch) {
  if(typeof str=='number') {
    str=String(str);
    if(arguments.length<3) ch='0';
  }
  var N=Math.abs(n);
  if(str.length>=N) {
    return n>=0 ? str.substr(0, n) : str.substr(str.length-N);
  }
  return (n>=0 ? str : '') + new Array(N-str.length+1).join((ch||' ').charAt(0)) + (n<0 ? str : '');
}

/*
 * toma una condicion como string:
 * "{nombre:['=','cliente.razonSocial'],desc:['like','sociedad anonima']}" cada
 * item esta formado por una clave representando al nombre del atributo y un
 * arreglo de dos strings donde el primer item es el operador y el 2do item es
 * el valor. La funcion evalua dinamicamente el valor en caso que sea el valor
 * de un campo del form La funcion retorna: "[(nombre =
 * cliente.razonSocial),(desc like sociedad anonima),[(nombre =
 * cliente.razonSocial),(desc like sociedad anonima)]]"
 */
function parseCondition(objj, sep) {
    var ret = "[";
    for(key in objj) {
    	if(key == "OR") {
    		ret += parseCondition(objj[key], ";");
    	} else {
			ret += "(";
			ret += key;
			ret += " " + objj[key][0];
			if(objj[key].length > 1){
				ret += " ";
				if(Object.isString(objj[key][1]))
					ret += $NV(objj[key][1]);
				else
					ret += objj[key][1];
			}
			if(sep == null)
				ret += "),";
			else
				ret += ")"+sep;
    	}
    }
    ret += "]";
	if(sep == null)
	    ret = ret.replace("),]",")]");
	else
	    ret = ret.replace(")"+sep+"]",")]");
    return ret;
}
function $NV(name){
	var obj = document.getElementsByName(name)[0];
	if(obj != undefined)
		return obj.value;
	else {
		obj = $(name);
		if(obj != undefined)
			return obj.value;
		else
			return name;
	}
}
function $N(name){
	try{
		return document.getElementsByName(name)[0];
	}catch(e){
		var obj = $(name);
		if(obj == undefined)
			return name;
		return obj;
	}
}

function GetValue(obj){
	return obj.value;
}
function SetValue(obj, value){
	obj.value = value;
	if(obj.onchange) obj.onchange();
}

function JavaListToArray(str)
{// La lista desde java viene separada por ', '
	return str.substring(1,str.length-1).split(", ");
}
function JavaMapToArray(str){
	var list = str.substring(1,str.length-1).split(", ");
	var map = {};
	for(var i = 0; i < list.length; ++i){
		var entry = list[i].split("=");
		map[entry[0]] = entry[1];
	}
	return map;
}

function Inspect(obj){
	var s = "";
	for(var prop in obj)
		s += "["+prop+"]="+obj[prop]+"\n";
	alert(s);
}

/* Dado un id de tabla, fila columna devuelve el contenido para dicha celda */
function getCellValue(table, row, column) {
    var table = document.getElementById(table);    
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    return rows[row].getElementsByTagName("td")[column].firstChild.nodeValue;
}

/* This function is used to change the style class of an element */
function swapClass(obj, newStyle) {
    obj.className = newStyle;
}

function isUndefined(value) {   
    var undef;   
    return value == undef; 
}

function checkAll(theForm) { // check all the checkboxes in the list
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
        var eName = e.name;
        if (eName != 'allbox' && 
            (e.type.indexOf("checkbox") == 0)) {
            e.checked = theForm.allbox.checked;        
        }
    } 
}

/* Function to clear a form of all it's values */
function clearForm(frmObj, gridId) {
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if(element.type.indexOf("text") == 0 || 
                element.type.indexOf("password") == 0) {
                    element.value="";
        } else if (element.type.indexOf("radio") == 0) {
            element.checked=false;
        } else if (element.type.indexOf("checkbox") == 0) {
            element.checked = false;
        } else if (element.type.indexOf("select") == 0) {
            for(var j = 0; j < element.length ; j++) {
                element.options[j].selected=false;
            }
            element.options[0].selected=true;
        }
    }
    if(gridId) {
    	// hay que limpiar la grilla
    	
    }
}

/* Function to get a form's values in a string */
function getFormAsString(frmObj) {
    var query = "";
    for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if (element.type.indexOf("checkbox") == 0 || 
            element.type.indexOf("radio") == 0) { 
            if (element.checked) {
                query += element.name + '=' + escape(element.value) + "&";
            }
        } else if (element.type.indexOf("select") == 0) {
            for (var j = 0; j < element.length ; j++) {
                if (element.options[j].selected) {
                    query += element.name + '=' + escape(element.value) + "&";
                }
            }
        } else {
            query += element.name + '=' 
                  + escape(element.value) + "&"; 
        }
    } 
    return query;
}

/*
 * Function to hide form elements that show through the search form when it is
 * visible
 */
function toggleForm(frmObj, iState) // 1 visible, 0 hidden
{
    for(var i = 0; i < frmObj.length; i++) {
        if (frmObj.elements[i].type.indexOf("select") == 0 || frmObj.elements[i].type.indexOf("checkbox") == 0) {
            frmObj.elements[i].style.visibility = iState ? "visible" : "hidden";
        }
    } 
}

/* Helper function for re-ordering options in a select */
function opt(txt,val,sel) {
    this.txt=txt;
    this.val=val;
    this.sel=sel;
}

/* Function for re-ordering <option>'s in a <select> */
function move(list,to) {     
    var total=list.options.length;
    index = list.selectedIndex;
    if (index == -1) return false;
    if (to == +1 && index == total-1) return false;
    if (to == -1 && index == 0) return false;
    to = index+to;
    var opts = new Array();
    for (i=0; i<total; i++) {
        opts[i]=new opt(list.options[i].text,list.options[i].value,list.options[i].selected);
    }
    tempOpt = opts[to];
    opts[to] = opts[index];
    opts[index] = tempOpt
    list.options.length=0; // clear
    
    for (i=0;i<opts.length;i++) {
        list.options[i] = new Option(opts[i].txt,opts[i].val);
        list.options[i].selected = opts[i].sel;
    }
    
    list.focus();
} 

/* This function is to select all options in a multi-valued <select> */
function selectAll(elementId) {
    var element = document.getElementById(elementId);
    len = element.length;
    if (len != 0) {
        for (i = 0; i < len; i++) {
            element.options[i].selected = true;
        }
    }
}

/*
 * This function is used to select a checkbox by passing in the checkbox id
 */
function toggleChoice(elementId) {
    var element = document.getElementById(elementId);
    if (element.checked) {
        element.checked = false;
    } else {
        element.checked = true;
    }
}

/*
 * This function is used to select a radio button by passing in the radio button
 * id and index you want to select
 */
function toggleRadio(elementId, index) {
    var element = document.getElementsByName(elementId)[index];
    element.checked = true;
}

/*
 * Esta funcion devuelve el radio seleccionado
 * 
 */
function getSelectedRadio(elementName) {
	
    var elements = document.getElementsByName(elementName);
    for(i=0;i<elements.length;i++)
    	if(elements[i].checked)
    		return elements[i];
    return null;
}

/*
 * Esta funcion setea el radio con valor v
 * 
 */
function setSelectedRadio(elementName, v) {
	
    var elements = document.getElementsByName(elementName);
    for(i=0;i<elements.length;i++)
    	if(elements[i].value == v)
    		elements[i].checked = true;
    	else
    		elements[i].checked = false;
}

/* This function is used to open a pop-up window */
function openWindow(url, winTitle, winParams) {
    winName = window.open(url, winTitle, winParams);
    winName.focus();
}


/* This function is to open search results in a pop-up window */
function openSearch(url, winTitle) {
    var screenWidth = parseInt(screen.availWidth);
    var screenHeight = parseInt(screen.availHeight);

    var winParams = "width=" + screenWidth + ",height=" + screenHeight;
        winParams += ",left=0,top=0,toolbar,scrollbars,resizable,status=yes";

    openWindow(url, winTitle, winParams);
}

/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
    var prefix = name + "=" 
    var start = document.cookie.indexOf(prefix) 

    if (start==-1) {
        return null;
    }
    
    var end = document.cookie.indexOf(";", start+prefix.length) 
    if (end==-1) {
        end=document.cookie.length;
    }

    var value=document.cookie.substring(start+prefix.length, end) 
    return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}

// This function is for stripping leading and trailing spaces
String.prototype.trim = function () {
    return this.replace(/^\s*(\S*(\s+\S+)*)\s*$/, "$1");
};
//This function is for stripping leading and trailing spaces
String.prototype.trimCR = function () {
    return this.replace(/[\n\r\t]/g, '').trim();
};

// This function is used by the login screen to validate user/pass
// are entered.
function validateRequired(form) {                                    
    var bValid = true;
    var focusField = null;
    var i = 0;                                                                                          
    var fields = new Array();                                                                           
    oRequired = new required();                                                                         
                                                                                                        
    for (x in oRequired) {                                                                              
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]]; 
              
           fields[i++] = oRequired[x][1];
            
           bValid = false;                                                                             
        }                                                                                               
    }                                                                                                   
                                                                                                       
    if (fields.length > 0) {element
       focusField.focus();
       alert(fields.join('\n'));                                                                      
    }                                                                                                   
                                                                                                       
    return bValid;                                                                                      
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId); 
    var startRow = 0;
    // workaround for Tapestry not using thead
    if (!table.getElementsByTagName("thead")[0]) {
	    startRow = 1;
    }
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    for (i=startRow; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.className+=' over' };
        rows[i].onmouseout = function() { this.className=previousClass };
        rows[i].onclick = function() {
            var cell = this.getElementsByTagName("td")[0];
            var link = cell.getElementsByTagName("a")[0];
            if (link.onclick) {
                call = link.getAttribute("onclick");
                if (call.indexOf("return ") == 0) {
                    call = call.substring(7);
                } 
                // this will not work for links with onclick handlers that
				// return false
                eval(call);
            } else {
            	if(typeof notificacionManager != 'undefined')
            		notificacionManager._click({'target': link});
                location.href = link.getAttribute("href");
            }
            this.style.cursor="wait";
            return false;
        }
    }
}

function highlightFormElements() {
    // add input box highlighting
    addFocusHandlers(document.getElementsByTagName("input"));
    addFocusHandlers(document.getElementsByTagName("textarea"));
}

function clearForm(oForm) {
	var elements = oForm.elements; 
	oForm.reset();
	for(i=0; i<elements.length; i++) {
		field_type = elements[i].type.toLowerCase();
			
		switch(field_type) {
			case "text": 
			case "password": 
			case "textarea":
		        case "hidden":	
				
				elements[i].value = ""; 
				break;
		       
			case "radio":
			case "checkbox":
		 			if (elements[i].checked) {
		  				elements[i].checked = false; 
				}
				break;
		
			case "select-one":
			case "select-multi":
		           		elements[i].selectedIndex = 0;
				break;
		
			default: 
				break;
		}
	}
}

function addFocusHandlers(elements) {
    for (i=0; i < elements.length; i++) {
        if (elements[i].type != "button" && elements[i].type != "submit" &&
            elements[i].type != "reset" && elements[i].type != "checkbox" && elements[i].type != "radio") {
            if (!elements[i].getAttribute('readonly') && !elements[i].getAttribute('disabled')) {
                elements[i].onfocus=function() {this.style.backgroundColor='#ffd';this.select()};
                elements[i].onmouseover=function() {this.style.backgroundColor='#ffd'};
                elements[i].onblur=function() {this.style.backgroundColor='';}
                elements[i].onmouseout=function() {this.style.backgroundColor='';}
            }
        }
    }
}

function radio(clicked){
    var form = clicked.form;
    var checkboxes = form.elements[clicked.name];
    if (!clicked.checked || !checkboxes.length) {
        clicked.parentNode.parentNode.className="";
        return false;
    }

    for (i=0; i<checkboxes.length; i++) {
        if (checkboxes[i] != clicked) {
            checkboxes[i].checked=false;
            checkboxes[i].parentNode.parentNode.className="";
        }
    }

    // highlight the row
    clicked.parentNode.parentNode.className="over";
}

// Permite agregarle nuevas funciones al onload sin perder las anteriores
function addOnLoad(newOnLoad) {
   var prevOnload = window.onload;
   if (typeof window.onload != 'function') {
      window.onload = newOnLoad;
   }
   else {
      window.onload = function() {
          prevOnload();
          newOnLoad();
      }
   }
}

// Esta funcion limpia todas las opciones de un drop down
// Ej: clearOptions($('contrato_producto')); donde contrato_producto es el id
// del s:select
function clearOptions(optionList) {
	for(i=optionList.length; i>=0; i--) {
		optionList[i] = null;
	}
}

// Esta funcion agrega una opcion a la lista de un drop down
function addToOptionList(optionList, optionValue, optionText) {
	// Agrego option al final de la lista
	optionList[optionList.length] = new Option(optionText, optionValue);
}

// Esta funcion agrega un arreglo como opciones a la lista de un drop down
// El arreglo viene de la forma {{id0, val0}, {id1, val1}, ... ,{idn, valn}}
function addArrayToOptionList(arr, optionList, addEmptyOption) {
	clearOptions(optionList);
	if(addEmptyOption)
		addToOptionList(optionList, "", "");
	if(arr != null) {
		for(i=0; i<arr.size(); i++) {
			addToOptionList(optionList, arr[i][0], arr[i][1]);
		}
	}
}

// Esta funcion devuelve el texto de la opciÃ³n seleccionada de un dropdown
function getSelectedOption(dd) {
	var i = dd.selectedIndex;
	return dd.options[i].text;
}
	 
// Devuelve un valor diferente de undefined
function getValidValue(val) {
    if(val == undefined)
        return null;
    return val;
}

function treeGridDetailPermanent(idTree) {
    var tree = SweetDevRia.$(idTree);
    for(var nodo in tree.nodes) {
    	SweetDevRia.$(tree.gridId).swapDetailVisibility(nodo);
    	var gridDetailDiv = SweetDevRia.DomHelper.get(tree.gridId + "_" + nodo + "_detailIcon");
    	if(gridDetailDiv)
    		SweetDevRia.DomHelper.setStyle(gridDetailDiv, "display", "none"); 	    	
    	gridDetailDiv = SweetDevRia.DomHelper.get(tree.gridId + "_" + nodo + "_detailIcon_left");
    	if(gridDetailDiv)
	    	SweetDevRia.DomHelper.setStyle(gridDetailDiv, "display", "none");
    	gridDetailDiv = SweetDevRia.DomHelper.get(tree.gridId + "_" + nodo + "_detailIcon_right");
    	if(gridDetailDiv)
	    	SweetDevRia.DomHelper.setStyle(gridDetailDiv, "display", "none");
    }
}
function checkTreeNode(idTree,idNode) {
	SweetDevRia.$(idTree).getNode(idNode).check(true, false);
}
function uncheckTreeNode(idTree,idNode) {
	SweetDevRia.$(idTree).getNode(idNode).uncheck(true, false);
}

/*
 * Por ajax recupera la entidad modelEntity, cuyo entityCodeAttrName es igual
 * entityCodeValue Copia en los controles idAttr y nameAttr, los valores de los
 * atributos de la entidad id y entityAttrName
 */
function valueListCodeChanged(modelEntity, entityCodeAttrName, entityCodeValue, idAttr, nameAttr, entityAttrName, nameLabel, notFoundWarningControlId, callbackFunction, clearFunction, modelEntityFilter, modelCodeEntityFilter) {
	// Si el codigo viene vacio, limpio el id y no hago llamada al ajax
	if(entityCodeValue.replace(/\s/g,"") == "") {
		$N(idAttr).value = "";
		return;
	}
	try{
		new Ajax.Request('entity.html',
			{
				parameters: {modelEntity: modelEntity, entityCodeAttrName: entityCodeAttrName, entityCodeValue: entityCodeValue, entityAttrName: entityAttrName, nameLabel: nameLabel, modelEntityFilter: eval(modelEntityFilter.replace(/[\^]/gm, "'")), modelCodeEntityFilter: eval(modelCodeEntityFilter.replace(/[\^]/gm, "'"))},
				onComplete: function(transport, json) {
					$N(idAttr).value = getValidValue(json.id);
					if(getValidValue(json.nameValue) == "NOT_FOUND") {
						$(notFoundWarningControlId).style.display = "block";
						if(clearFunction!=''){
							eval(clearFunction+"()");
						}
					} else {
						$(notFoundWarningControlId).style.display = "none";
						$N(nameAttr).value = getValidValue(json.nameValue);
						if(callbackFunction!=''){
							eval(callbackFunction+"()");
						}
					}
				}
			});
	}catch(e){
		alert(e);
	}
}

function valueListSetNameOnChange(id, nameAttr){
	try{
		var obj = $N(nameAttr);
		obj.__onChange__ = obj.onchange;
		obj.onchange = function(){
			if(typeof obj.__onChange__ != 'undefined' && obj.__onChange__ != null)
				obj.__onChange__();
			$(id + '_valueListButtons_add').onclick();
		}
	}catch(e){
		alert(e);
	}
}

function valueListClear(idAttr, entityCodeName, nameAttr) {
	$N(idAttr).value="";
	if(entityCodeName != "") {
		$N(entityCodeName).value="";
	}
	$N(nameAttr).value="";
}

function valueListDisable(id, idAttr, entityCodeName, nameAttr, readonly) {
	$N(idAttr).readOnly=readonly;
	if(entityCodeName != "") {
		$N(entityCodeName).readOnly=readonly;
	}
	$N(nameAttr).readOnly=readonly;
	if(readonly)
		$(id + "_valueListButtons").style.display="none";
	else
		$(id + "_valueListButtons").style.display="inline";
}

function setValueToValueList(idAttr, idAttrVal, entityCodeName, entityCodeNameVal, nameAttr, nameAttrVal) {
	try {
	$N(idAttr).value=idAttrVal;
	if(entityCodeName != "") {
		$N(entityCodeName).value=entityCodeNameVal;
	}
	$N(nameAttr).value=nameAttrVal;
	} catch(e) {
		alert(e);
	}
}
// Dado un arreglo de checkboxes, devuelve una lista de valores con los
// checkeado
// La lista del siguiente tipo: v1, v2, ..., vn
function getCheckedValues(chekboxlist) {
	var checkboxes = document.getElementsByName(chekboxlist);
	var checkboxValues = "";
	for(i=0;i<checkboxes.length;i++) {
		var checkbox = checkboxes[i];
		if(checkbox.checked)
			checkboxValues += checkbox.value + ",";
	}
	if(checkboxValues != "" && checkboxValues[checkboxValues.length-1] == ",")
		checkboxValues = checkboxValues.substring(0,checkboxValues.length-1);
	return checkboxValues;
}
// Realiza lo inverso de la funcion anterior, checkea una lista de checkboxes
function setCheckedValues(chekboxlist, valuesList) {
	var checkboxes = document.getElementsByName(chekboxlist);
	var values = $N(valuesList).value;
	
	for(i=0;i<checkboxes.length;i++) {
		var checkbox = checkboxes[i];
		if(values.indexOf(checkbox.value) != -1)
			checkbox.checked = true;
	}
}

function commaOnNumericPad(event) {
	if(event.keyCode==110) {
		var txt = event.target;
	    var oldtext = txt.value;
	    var curpos = txt.selectionStart;
	    pretext = oldtext.substring(0,curpos-1);
	    posttest = oldtext.substring(curpos,oldtext.length);
	    txt.value = pretext + "," + posttest;
	}
}

function setCommaOnNumericPad() {
	  var formElems = document.getElementsByTagName('INPUT');

	  for(var i = 0; i < formElems.length; ++i){
		  formElems[i].onkeyup = commaOnNumericPad;
	  }
}

// ****************************//

// Funciones de DropDowns Search
// Java Script to Handle AutoSearch
function selectKeyDown(event) {
  // Delete Key resets previous search keys
  if(event.keyCode == 46)
      clr();
}

function selectKeyPress(event) {
  // Notes:
  // 1) previous keys are cleared onBlur/onFocus and with Delete key
  // 2) if the search doesn't find a match, this returns to normal 1 key
  // search setting returnValue = false below for ALL cases will
  // prevent default behavior
  
  // TODO:
  // 1) add Netscape handling
  

  var sndr = event.target;
  var pre = $("keys").value;
  var key = event.keyCode;
  var char = String.fromCharCode(key);

  // "i" -> ignoreCase
  var re = new RegExp("^" + pre + char, "i"); 

  for(var i=0; i<sndr.options.length; i++)
  {
      if(re.test(sndr.options[i].text))
      {
          sndr.options[i].selected=true;
          $("keys").value += char;
          event.returnValue = false;
          break;
      }
  }
}

function clr() {
  $("keys").value = "";
}

// Agrega, segun el valor de enabled, la funcionalidad de Search a los DropDowns
function initializeDropDownSearch(enabled)
{
	if(enabled && document.forms[0])
	{
		try 
		{
			// Agrega el Hidden al Documento 'keys'
			inputHidden = document.createElement('input');
			inputHidden.setAttribute('type','hidden');
			inputHidden.setAttribute('id','keys');
			document.forms[0].appendChild(inputHidden);
			
			// debugger;
			if(document.getElementsByTagName) {
				select = document.getElementsByTagName('select');
				for(var i = 0; i < select.length; i++) {
					select[i].onkeypress = selectKeyPress;
					select[i].onkeydown = selectKeyDown;
					select[i].onblur = clr;
					select[i].onfocus = clr;
				}
			}
		} catch(e) {
			alert(e.message)
		}
	}
}

// Si cuando se presiona <Enter> el valor de element fue cambiado y el elemento tiene un
// onchange, pospone el submitOnEnterKey hasta que se ejecute element.submitOnEnterKey()
// element.submitOnEnterKey() debe ser ejecutado al finalizar el onChange o el onComplete
// de Ajax si el onchange dispara un Ajax
function delay_submitOnEnterKey_whenValueChange(element) {
	if(element.onchange){
		element.submitOnEnterKey = function(event){
			element.__oldValue = this.value;
			element.form.pause_submitOnEnterKey = false;
			if(element.form.__submitOnEnterKey_BTN)
				element.form.__submitOnEnterKey_BTN.click();
		};
		element.observe('focus', function(event){
			this.form.__submitOnEnterKey_BTN = null;
			this.form.pause_submitOnEnterKey = true;
			this.__oldValue = this.value;
		});
		element.observe('blur', function(event){
			if(this.__oldValue == this.value)
				this.form.pause_submitOnEnterKey = false;
		});
		element.observe('keypress', function(event){
		    if ( event.keyCode == Event.KEY_RETURN  || event.which == Event.KEY_RETURN )
		    	this.blur();
		});
	}
}

var __submitOnEnterKey_suspendido__ = false;
// Habilita el submit del form cuando se presiona enter, si es una pagina con filtro
function submitOnEnterKey() {
	document.observe('keypress', function(event){
		if(__submitOnEnterKey_suspendido__) return;
	    if ( event.keyCode == Event.KEY_RETURN  || event.which == Event.KEY_RETURN ) {
	    	// Solamente hacemos submit on enter en los filtros, es decir hay un meta con nombre filtro
	    	var metaArray = document.getElementsByName('filtro')
	    	if(metaArray.length == 1) {
		    	var btn = document.forms[0].getInputs('submit')[0];
		    	if(btn != null){
		    		if(btn.form.pause_submitOnEnterKey)
		    			btn.form.__submitOnEnterKey_BTN = btn;
		    		else
		    			btn.click();
		    		Event.stop(event);
		    	}
	    	}
	    }
	});
}
// Suspende o rehabilita el tratamiento de submitOnEnterKey
function submitOnEnterKey_suspend(b) {__submitOnEnterKey_suspendido__ = b;}

window.onload = function() {
    highlightFormElements();
    if ($('successMessages')) {
        new Effect.Highlight('successMessages');
        // causes webtest exception on OS X :
		// http://lists.canoo.com/pipermail/webtest/2006q1/005214.html
        // window.setTimeout("Effect.DropOut('successMessages')", 3000);
    }
    if ($('errorMessages')) {
        new Effect.Highlight('errorMessages');
    }
    
    // Initialize menus for IE
    var primaryNav = $("primary-nav");
    if (primaryNav) {
        // Emparcha overs de menubars
        var navItems = primaryNav.getElementsByTagName("li");
        for (var i=0; i<navItems.length; i++) {
            if (navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }
        // Verifica si el current es el correcto
        var aItems = primaryNav.getElementsByTagName("a");
        var aCurrentActual = null;
        var aCurrentCorrecto = null;
        for (var i=0; i<aItems.length; i++) {
        	if(aItems[i].className.match(/current/i))
        		aCurrentActual = aItems[i];
        	if(this.location.href == aItems[i].href)
        		aCurrentCorrecto = aItems[i];
        }
        if(aCurrentCorrecto != null && aCurrentActual != aCurrentCorrecto){
        	if(aCurrentActual) aCurrentActual.className = "";
        	if(aCurrentCorrecto) aCurrentCorrecto.className = "current";
        }
    }
    
    setCommaOnNumericPad();
    initializeDropDownSearch(true);
    submitOnEnterKey();
    
//    anulaRefresh();
    anulaBack();
}
function setFormButtonInfo(name, obj){
	var form = document.forms[0];
	if(typeof form != 'undefined'){
		form._submitBtn=name;
		form._submitBtnObj=obj;
	}
}
function esRefresh (e){
	var lastClick = esRefresh.___last_click___;
	if(typeof lastClick != 'undefined' && lastClick != null 
	&& (new Date() - lastClick.time) < 1000 && lastClick.tag.match(/IMG|A|SUBMIT/i))
			return false;
	return true;
}
function doDocumentRedirect(url){
	esRefresh.___last_click___ = {'tag' : 'A', 'time': new Date()};
	document.location.href = url;
}
function anulaRefresh(){
	document.body.onclick = function (e){
		esRefresh.___last_click___ = {'tag' : e.target.tagName == 'INPUT' ? e.target.type : e.target.tagName, 
									  'time': new Date()};
	};
	window.onbeforeunload = function (e) {
		if(esRefresh(e)) {
			var msg = "Al recargar la página se pueden producir efectos no deseados. Desea continuar?";
			(e || window.event).returnValue = msg;
			return msg;
		}
	};
}
function anulaBack(){
    if (typeof history.pushState === "function") {
        history.pushState("jibberish", null, null);
        window.onpopstate = function () {
            history.pushState('newjibberish', null, null);
        };
    }
    else {
        var ignoreHashChange = true;
        window.onhashchange = function () {
            if (!ignoreHashChange) {
                ignoreHashChange = true;
                window.location.hash = Math.random();
            }
            else {
                ignoreHashChange = false;   
            }
        };
    }
}

// Show the document's title on the status bar
window.defaultStatus=document.title;

// funciones de formato de nro contrato
function formatNroContrato(name) {
	var contrato=$N(name).value;
	contrato = contrato.trim();
	if(contrato != "") {
		if(contrato[0]=='d' || contrato[0]=='D') {
			contrato = contrato.split('-').join('');
			$N(name).value = 'D-'+contrato.substring(1,3)+"-"+contrato.substring(3);
		} else {
			contrato = contrato.split('/').join('');
			if(contrato.length == 8) // Contrato comun 13645924 -> 13/64592/4
				$N(name).value = contrato.substring(0,2)+"/"+contrato.substring(2,7)+"/"+contrato.substring(7);
			else if(contrato.length > 8) // Contrato comun 13645924003 -> 13/64592/4/003
				$N(name).value = contrato.substring(0,2)+"/"+contrato.substring(2,7)+"/"+contrato.substring(7,8)+"/"+contrato.substring(8);
		}
	}
}

function formatNroContratoDeposito(name) {
	formatNroContrato(name);
}

function formatNroContratoAmbos(name) {
	formatNroContrato(name);
}
// No utilizar mas esta funcion
function formatNroContratoKeyPress(input, e) {
	formatNroContrato(input.name);
	/*
	var key = e.keyCode;
	if (key != 8 && key != 46){
		var nro = input.value;
		if (nro[0]=='d'){
			input.value = 'D-';
		}
		if (nro[0]=='D'){
			if (nro.length == 1 || nro.length == 4){
				input.value = nro + '-';
			}
		} else {
			if (nro.length == 2 || nro.length == 8){
				input.value = nro + '/';
			} else if (nro.length == 10){
				input.value = '/' + nro;
			} 
		}
	}
	*/
	return true;
}

function formatTFieldToUnidad(tF, formatoUnidad){
	tF.value=formatStringToUnidad(tF.value , formatoUnidad);
}

//elimina los puntos de un string
function quitarPuntosMillar(strVal){
	return strVal.replace(/[.]/g,'');
}

function formatStringToUnidad(sVal, formatoUnidad){
	sVal = sVal.replace(",",".");
	if(isNaN(sVal) || sVal == ""){
		sVal = "";
	} else {
		sVal=formatToUnidad(parseFloat(sVal), formatoUnidad);
	}
	return sVal;
}

function formatToUnidad(val, formatoUnidad){
	var decimales=formatoUnidad.lastIndexOf('.')+1;
	decimales = decimales > 0 ? formatoUnidad.length - decimales : 0;
	return val.toFixed(decimales).replace(".",",");			
}

function formatInt(text) {
	formatXDecimales(text,0);
}

function formatXDecimales(tF,dec) {
	tF.value = tF.value.replace(",",".");
	if(isNaN(tF.value)|| tF.value == "" ){
		tF.value = "";
	} else {
		tF.value = parseFloat(tF.value).toFixed(dec).replace(".",",");
	}
}


//armo fechas iguales de datos con distinto formato para compararlas	
function fechaSegunFormato(strFecha){
	var arrayFecha; 
	if(strFecha.indexOf('/')>0){
		arrayFecha = strFecha.split('/');
		//anio-mes-dia
		return new Date(arrayFecha[2], arrayFecha[1]-1, arrayFecha[0]);	
	}else if (strFecha.indexOf('-')>0){
		arrayFecha = strFecha.split(' ')[0].split('-');
		//anio-mes-dia
		return new Date(arrayFecha[0], arrayFecha[1]-1, arrayFecha[2]);
	}
	
}	

//oculta/muestra un div(por id)
function showDiv(divId,mostrar){
	var div=$(divId);
	if(mostrar){
		div.setStyle({width:'auto',visibility:'visible'});
	}
	else{
		div.setStyle({width:0,visibility:'hidden'});
	}
}

// oculta/muestra un componente usando display para que no ocupe lugar
// estando oculto
function displayElement(elementId, mostrar) {
	var element = $(elementId);
	if( element != null && element.style != null ){
		display = mostrar ? "inline" : "none";
		element.style.display = display;
	}
}

//Des/habilita un DatePicker
function habilitarDatePicker(bHabilitar,sId){
	var dp=$(sId);
	if(bHabilitar)
		dp.childNodes[1].enable();
	else
		dp.childNodes[1].disable();
	dp.childNodes[3].setStyle({visibility:(bHabilitar?'visible':'hidden')});
}

//funcion para esconder un boton por id
function esconderBoton(idButton,bEsconder){
	if(bEsconder)
		document.getElementById(idButton).type="hidden";
	else
		document.getElementById(idButton).type="button";
}

// Le asigna comportamiento readonly a una componente
// Util para los s:select que no tienen la propiedad
function SetReadonlyBehaviour(objName){
	var obj = $N(objName);
	obj.onmouseover=function (){this.disabled=true;};
	obj.onmouseout=function (){this.disabled=false;};
}

// setea un valor a una celda de una grilla
function setCellValue(gridId, rowId, colId, val) {
	var cellId = SweetDevRia.$(gridId).id + "_cell_" + rowId + "_" + colId;
	var cell = document.getElementById(cellId);
	var newVal = parseFloat(val) ? val.toString().replace(".", ",") : val;
	cell.innerHTML = newVal;
	SweetDevRia.$(gridId).updateCellData(rowId, colId, newVal);
	SweetDevRia.$(gridId).updateServerModel(true);
	if (SweetDevRia.$(gridId).getColumnEditable(colId)) {
		var edit = SweetDevRia.$(cellId+"_edit");
		edit.setValue(newVal);
	}
}

// Transforma boolean en SI o NO
function getSiNo(pBoolean){
	if(pBoolean != null && pBoolean == "true")
		return '<fmt:message key="valor.si"/>';
	return '<fmt:message key="valor.no"/>';
}

/**
 * Convierte el String resultante de serializar el Form a un Hash para poder pasar
 * sus valores por Ajax
 * @param serialized resultado de Form.serialize()
 * @returns {Hash}
 */
function convertSerializedToHash(serialized) {
	var hash = new Hash();
	var data = serialized.split("&");
	for(var i = 0; i < data.size(); i++) {
		var keyValue = data[i].split("=");
		if (unescape(keyValue[0]).indexOf("action:") == -1) {
			hash.set(unescape(keyValue[0]), unescape(keyValue[1]));
		}
	}
	
	return hash;
}

function formatTipoOpMAT(value) {
	var newValue = value;
	if (value != null && !value.trim().empty()) {
		switch(value)
		{
		case "C":
		  newValue = "Call"
		  break;
		case "F":
		  newValue = "Futuro"
		  break;
		case "P":
		  newValue = "Put"
		  break;
		case "T":
		  newValue = "Futuros s/Base"
		  break;
		case "I":
		  newValue = "Inmediato"
		  break;
		case "A":
		  newValue = "Acciones"
		  break;  
		case "L":
		  newValue = "Titulos"
		  break;
		case "N":
		  newValue = "Fondos"
		  break;
		case "D":
		  newValue = "Documentos"
		  break;
		}
	}
	
	return newValue;
}

function formatNombreMercado(value) {
	var newValue = value;
	if (value != null && !value.trim().empty() && value.toUpperCase() == "MERCADO A TERMINO BA") {
		newValue = "MAT BA";
	}
	
	return newValue;
}

function formatCondicionMAT(value) {
	var newValue = value;
	if (value != null && !value.trim().empty() && value.toUpperCase() == "C") {
		newValue =  "Compra";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "V") {
		newValue =  "Venta";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "S") {
		newValue =  "Suscripción FCI";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "U") {
		newValue =  "Suscripción Doc";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "R") {
		newValue =  "Rescate";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "L") {
		newValue =  "Licitación";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "E") {
		newValue =  "Entrada";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "A") {
		newValue =  "Salida";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "N") {
		newValue =  "Renovación";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "T") {
		newValue =  "Alquiler Tomador";
	} else if (value != null && !value.trim().empty() && value.toUpperCase() == "O") {
		newValue =  "Todas";
	}
	
	return newValue;
}

function formato_numero(numero, decimales, separador_decimal, separador_miles){ // v2007-08-06
    numero=parseFloat(numero);
    if(isNaN(numero)){
        return "";
    }

    if(decimales!==undefined){
        // Redondeamos
        numero=numero.toFixed(decimales);
    }

    // Convertimos el punto en separador_decimal
    numero=numero.toString().replace(".", separador_decimal!==undefined ? separador_decimal : ",");

    if(separador_miles){
        // Añadimos los separadores de miles
        var miles=new RegExp("(-?[0-9]+)([0-9]{3})");
        while(miles.test(numero)) {
            numero=numero.replace(miles, "$1" + separador_miles + "$2");
        }
    }
    return numero;
}

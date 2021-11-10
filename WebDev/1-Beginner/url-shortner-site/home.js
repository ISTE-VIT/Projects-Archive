var linklist=JSON.parse(localStorage.getItem("allurl"));
window.onload = () => {
    var nav=window.innerWidth;
    console.log(nav);
    if(nav<600)
    {
        document.getElementById("navbar").innerHTML='<div id="leftnav"><button id="dropbutton" onclick="addnav()"><i class="fas fa-bars"></i></button><h4>goodShort</h4></div><div id="rightnav"><button id="login">Login</button><button id="signup">Signup</button></div>';
    }
    if(linklist==null){
        linklist={list:[]};
        console.log("null");
    }
    else{
        document.getElementById("loader").style.display="inherit";
        for (var i = 0; i < linklist.list.length; i++)
        {
            console.log(linklist);
            console.log(i);
            //get data
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "https://api.shrtco.de/v2/info?code="+linklist.list[i]); //shortened link
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send();
            var data={};
            xhr.onload=function()
            {
              if(this.status==200)
              {
                data=JSON.parse(this.responseText)
                console.log(data);
                var count=data.result.code;
                console.log(data.result.url);
                htmlString ='<div class="outputlink"><div class="input"><h6>'+data.result.url+'</h6></div><div class="output"><h6 id="result">shrtco.de/'+data.result.code+'</h6><button class="copy" id="link'+count+'" onclick="copyto(link'+count+')">copy</button></div></div>';
                document.getElementById("outputDiv").innerHTML+=(htmlString);
                
              }
              else
              {
                data=JSON.parse(this.responseText)
                
              }
            }
            //get data
            
        }
        document.getElementById("loader").style.display="none";
    }
}

const shorten = () => 
{
    var data = {
        "url":document.getElementById("userlink").value
    };
    if(data.url.includes("https://rel.ink/")==false){
     console.log(data.url) 
     document.getElementById("loader").style.display="inherit";
        var xhr = new XMLHttpRequest();
        xhr.addEventListener("readystatechange", function() 
        {
          if(this.readyState === 4) 
          {
              //console.log(this.status);
              if(this.status==200 || this.status==201)
              {
        
                    let newData = JSON.parse(this.responseText)

                    console.log(newData.result)
                    
                    console.log(linklist.list.includes(newData.result.code));
                    if(linklist.list.includes(newData.result.code)==false){
                        linklist.list.push(newData.result.code);
                        var j=linklist.list.length;
                        var count=newData.result.code;
                        htmlString ='<div class="outputlink"><div class="input"><h6>'+data.url+'</h6></div><div class="output"><h6 id="result">'+newData.result.short_link+'</h6><button class="copy" id="link'+count+'" onclick="copyto(link'+count+')">copy</button></div></div>'
                        outputDiv.innerHTML+=htmlString;
                        localStorage.setItem("allurl",JSON.stringify(linklist))   
                    }
                    else{
                        alert("this link has already been shortened");
                    }
                    document.getElementById("loader").style.display="none";
              }
              else{
                  alert("invalid link");
              }
          }
        });
        xhr.open("POST", "https://api.shrtco.de/v2/shorten?url="+data.url);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send();
    }
    else{
        alert("this link has already been shortened");
    }   
        
}
const addnav = () => {
    document.getElementById("dropdown").innerHTML='<div id="content"><a href="#infoDiv" onclick="removenav()">Home</a><a href="#scroll"  onclick="removenav()">Links</a><a href="#statistics" onclick="removenav()">Statistics</a></div>';
    var tOpac=0;
    var id3=setInterval(frame3,0.03)
    function frame3() {
        if (tOpac == 150) {
            clearInterval(id3);
        } else {
            tOpac=tOpac+3;
            document.getElementById("dropdown").style.height = tOpac+'px';

        }
    }
    document.getElementById("navbar").innerHTML='<div id="leftnav"><button id="dropbutton" onclick="removenav()"><i class="fas fa-times"></i></button><h4>goodShort</h4></div><div id="rightnav"><button id="login">Login</button><button id="signup">Signup</button></div>';
}
const removenav = () => {
    document.getElementById("dropdown").innerHTML='';
    var tOpac=150;
    var id3=setInterval(frame3,0.01)
    function frame3() {
        if (tOpac == 0) {
            clearInterval(id3);
        } else {
            tOpac=tOpac-3;
            document.getElementById("dropdown").style.height = tOpac+'px';

        }
    }
    document.getElementById("navbar").innerHTML='<div id="leftnav"><button id="dropbutton" onclick="addnav()"><i class="fas fa-bars"></i></button><h4>goodShort</h4></div><div id="rightnav"><button id="login">Login</button><button id="signup">Signup</button></div>';
}
const getStarted = () => {
    console.log("working");
    var elemt=document.getElementById("scroll");
    elemt.scrollIntoView();
    
};
const copyto = (n) => {
    console.log(n);
    //Before we copy, we are going to select the text.
    var text = (n).previousSibling;
    var selection = window.getSelection();
    var range = document.createRange();
    range.selectNodeContents(text);
    selection.removeAllRanges();
    selection.addRange(range);
    //add to clipboard.
    document.execCommand('copy');
    (n).innerHTML="copied!";
    (n).style.backgroundColor="#1a2e35";
    alert("copied link: "+text.innerHTML)
    console.log(n);
}

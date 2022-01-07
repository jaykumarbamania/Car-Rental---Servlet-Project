function limitText(classnameTxts,length){
    for (let i = 0; i < classnameTxts.length; i++) {
        const text = classnameTxts[i].innerHTML;
        if(text.length > length){
            classnameTxts[i].innerHTML = text.substring(0,length) + '.....'
        }
    }
}
  $(document).ready(function(){
    
    let headlinestexts = document.getElementsByClassName('headlines')
    console.log(headlinestexts[0].innerHTML)
    limitText(headlinestexts,60)

    let highlightstexts = document.getElementsByClassName('highlights')
    console.log(highlightstexts.length)
    limitText(highlightstexts,150)

  })
document.getElementById('loadmore').style.display = 'none';

function divScroll() {
    let d = document.getElementById('scrollDiv');
    let wholeHeight = d.scrollHeight; 
    let scrollTop = d.scrollTop; 
    let divHeight = d.clientHeight;

    
    if(scrollTop + divHeight >= wholeHeight){
        let more = document.getElementById('loadmore');
        more.style.display = 'block';
        setTimeout(() => {
            document.getElementById('menu').appendChild(create('2018-05-01','18:00','标题1','标题2'));
            document.getElementById('menu').removeChild(more);
            document.getElementById('menu').appendChild(more);
        }, 1000);
        
    }
}

function create(time1,time2,title1,title2){
    let li = document.createElement('li');

    let h4 = document.createElement('h4');
    h4.innerText = time1;

    let d1 = document.createElement('div');
    d1.className = 'list1';

    let ico = document.createElement('span');
    ico.className = 'ico';

    let t1 = document.createElement('span');
    t1.className = 'title1';
    t1.innerHTML = '<em>'+ title1 +'</em>';

    let dot = document.createElement('span');
    dot.className = 'dot';
    dot.innerHTML = '<em>.</em>';

    let time = document.createElement('span');
    time.className = 'time1';
    time.innerText = time2;

    let line = document.createElement('span');
    line.className = 'line1';

    let link = document.createElement('a');
    link.className = 'title2';
    link.innerText = title2;

    let num = document.createElement('span');
    num.className = 'num1';
    num.innerHTML = '<em class="numbg"></em><em>24人已观看</em>';

    let lback = document.createElement('a');
    lback.className = 'lback';
    lback.innerText = '回顾';

    d1.appendChild(ico);
    d1.appendChild(t1);
    d1.appendChild(dot);
    d1.appendChild(time);
    d1.appendChild(line);
    d1.appendChild(link);
    d1.appendChild(num);
    d1.appendChild(lback);
    li.appendChild(h4);
    li.appendChild(d1);

    return li;
}
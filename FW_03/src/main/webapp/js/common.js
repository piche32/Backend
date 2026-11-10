// @charset "UTF-8";

const getFetch = async (url, param, isXml) => {
    try {
        const queryString = new URLSearchParams(param).toString();
        const response = await fetch(url + "?" + queryString);
        let result = "";
        if (isXml) {
            result = await response.text();
        } else {
            result = await response.json();
        }
        console.log("요청 URL: " + url, param, result);
        return result;
    } catch (e) {
        console.log(e);
        throw e;
    }
};
/**
 * post 처리를 위한 메서드로 
 */
const postFetch = async (url,body) => {
    try {
        const response = await fetch(url, {
            method: "post",
            headers:{
                "Content-Type":"application/x-www-form-urlencoded",    
            },            
            body: body
        });
        
        let result =  await response.json();
        console.log("요청 URL: " + url, body, result);
        return result;
    } catch (e) {
        console.log(e);
        throw e;
    }
};


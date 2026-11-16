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
const postFetch = async (url, body) => {
    try {
        const response = await fetch(url, {
            method: "post",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: body
        });

        let result = await response.json();
        console.log("요청 URL: " + url, body, result);
        return result;
    } catch (e) {
        console.log(e);
        throw e;
    }
};

const getCoords = (address) => {
    const geocoder = new kakao.maps.services.Geocoder();

    return new Promise((resolve, reject) => {
        geocoder.addressSearch(address, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                return resolve({ x: result[0].x, y: result[0].y });
            } else {
                reject("주소 검색 실패: " + address);
            }
        });
    });
}


// marker 목록
const markers = [];

const updateMap = async (map, infos) => {
    markers.map((m) => m.setMap(null)).length = 0; // 마커와 경계 초기화
    const bounds = new kakao.maps.LatLngBounds(); // 지도 경계 초기화
    const promises = []; // 좌표를 비동기로 처리하기 위한 Promise 배열
    try {
        for (let info of infos) {
            if (!info.x || !info.y) {
                const promise = getCoords(info.address)
                promises.push(promise);
            }
        }
        await Promise.all(promises);

        for (let info of infos) {
            // 잘못된 좌표가 들어오는 경우는 filtering
            if (124.5 <= info.x && info.x <= 132 && 33.0 <= info.y && info.y <= 38.6) {
                setOverlay(info, bounds);
            } else {
                console.warn("좌표가 유효하지 않습니다:", info);
            }
        }
        // 지도 경계 설정
        map.setBounds(bounds);
    } catch (e) {
        console.log(e);
    }
};

const setOverlay = (info, bounds) => {
    const coords = new kakao.maps.LatLng(info.y, info.x);
    bounds.extend(coords);
    const marker = new kakao.maps.Marker({ map: map, position: coords });
    markers.push(marker);

    // https://apis.map.kakao.com/web/sample/addMarkerMouseEvent/
    // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
    const iwContent = `<div style="padding:5px;">${info.label}</div>`;

    // 인포윈도우를 생성합니다
    const infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
    });

    // 마커에 마우스오버 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, "mouseover", function() {
        // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);
    });

    // 마커에 마우스아웃 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, "mouseout", function() {
        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
        infowindow.close();
    });
};

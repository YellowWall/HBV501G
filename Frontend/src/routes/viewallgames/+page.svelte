<script>
    import {page} from '$app/stores';
    import {onMount} from 'svelte';
    import {browser} from '$app/environment';

    let backendRoute = 'http://localhost:8080/games/';
    
    async function fetchPosts(){
        const urlParams = new URLSearchParams($page.url.search);
        if(browser){
            token = window.sessionStorage.getItem('authenticatorTocen');
        }
        console.log(token);
        const res = await fetch(
            backendRoute,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                'Authorization': "Bearer "+token}
            }
        )
        const json = await res.json();
        console.log(json);
        return json;
    }
</script>
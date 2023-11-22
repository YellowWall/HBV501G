<script>
    import {browser} from '$app/environment';
    import {goto} from '$app/navigation';
    export let gameId;
    export let id;
    export let username;
    export let yeets;
    if(yeets == null){
        yeets = 0;
    };
    if(id == null){
        id = null;
    };
    if(gameId== null){
        gameId = null;
    }
    if(username == null){
        if(browser){
            username = window.sessionStorage.getItem("Username");
        }
    }
    
    let backendRoute = 'https://hbv501g-backend.onrender.com/';
    
    async function postHole(){
        const res = await fetch(
                backendRoute +"hole/postform",
                {
                    method: 'POST',
                    headers: {"Content-Type": "application/json",
                            "Authorization": "Bearer "+window.sessionStorage.getItem('authenticatorTocen')},
                    body: JSON.stringify({
                        gameId,username,yeets
                    }
                    )
                }
            )
            return await res.json();
    }
    async function patchHole(){
        const res = await fetch(
                backendRoute +"hole/updateScore",
                {
                    method: 'PATCH',
                    headers: {"Content-Type": "application/json",
                            "Authorization": "Bearer "+window.sessionStorage.getItem('authenticatorTocen')},
                    body: JSON.stringify({
                        id,yeets
                    }
                    )
                }
            )
            const json = await res.json();
            if(json.success){
                gameId = json.data.gameId;
            }
    }
    function submitPost(){
        if(id==null){
            postHole();
        }else{ patchHole()};
    }
    

</script>
<main>
    {#if id !=null}
    <p>Uppfæra skráningu holu</p>
    {:else}
    <p>Skrá nýja holu</p>
    {/if}
    <form action="">
        <input 
        bind:value={yeets}
        type="number"
        min="0" max="50"
        name="Köst"
        placeholder={yeets}
        >
        <input 
        on:click|preventDefault={submitPost}
        type="submit"
        value="Senda">
    </form>
</main>
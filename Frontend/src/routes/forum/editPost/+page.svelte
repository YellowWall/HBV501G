<script>
	import DisplayPost from "../../../components/DisplayPost.svelte";
	import PostForm from "../../../components/PostForm.svelte";
    import {page} from '$app/stores';
    import {browser} from '$app/environment';
    let backendRoute = 'http://localhost:8080/forum/';
    let allow = false;

    async function fetchPosts(){
        let username;
        let token;
        if(browser){
            token = window.sessionStorage.getItem('authenticatorTocen');
            username = window.sessionStorage.getItem('Username');
        };
        const urlParams = new URLSearchParams($page.url.search);
        const id = urlParams.get('id');
        const res = await fetch(
            backendRoute + "post/focus?postid="+id,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json",
                "Authorization":"Bearer "+token}
                
            }
        )
        const json = await res.json();
        console.log(json.data.username);
        console.log(username);
        if(json.data.username == username){
            allow = true;
        }

        return json;
    }

</script>
<main>
    <h5>Edit Post</h5>
    {#await fetchPosts()}
        <p>loading</p>
    {:then post} 
        {#if !allow}
        <bold>You do not have permission to edit that post </bold>
        {:else}
        <svelte:component this={DisplayPost} {...post.data}/>
        <svelte:component this={PostForm} {...post.data}/>
        {/if}
    {/await}
    
</main>
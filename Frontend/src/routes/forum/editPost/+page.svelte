<script>
	import DisplayPost from "../../../components/DisplayPost.svelte";
	import PostForm from "../../../components/PostForm.svelte";
    import {page} from '$app/stores';
    import {browser} from '$app/environment';
    let backendRoute = 'http://localhost:8080/forum/';
    async function fetchPosts(){
        let token;
        if(browser){
            token = window.sessionStorage.getItem('authenticatorTocen');
        }
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
        return json;
    }

</script>
<main>
    <h5>Edit Post</h5>
    {#await fetchPosts()}
        <p>loading</p>
    {:then post} 
        <svelte:component this={DisplayPost} {...post.data}/>
        <svelte:component this={PostForm} {...post.data}/>
        
    {/await}
    
</main>
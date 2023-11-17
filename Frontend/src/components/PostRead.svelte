<script>
    import {goto,} from '$app/navigation';
    import {page} from '$app/stores';
    import {onMount} from 'svelte';

    let datafetched = false;

    const backendRoute = 'http://localhost:8080/forum/post/focus';
    async function fetchPosts(){
        let disptitle = "no title";
        let disptext = "no text";
        let dispuser = "no user";
        let datagot = "no data got";
        const urlParams = new URLSearchParams($page.url.search);
        const postid = urlParams.get('postid');
        const res = await fetch(
            backendRoute+'?postid='+postid,
            {
                method: 'GET',
                headers: {"Content-Type": "application/json"}
            }
        )
        const json = await res.json();
        console.log(json);
        return json;
        /*
        datagot = json.message;
        if(json.success){
            let forumPosts = json.data;
            datagot = "some data got"
            disptitle = forumPosts.title;
            disptext = forumPosts.text;
            datafetched = true;
            console.log(forumPosts.title);
            console.log(forumPosts.text);
            return forumPosts; 
        }
        return {disptitle,disptext,datagot}*/
    }  
    let datafetch = fetchPosts();

    
</script>
<main>
<h1>FORUM</h1>
{#await datafetch}
    <p>loading</p>
{:then json}
    <pre>
        <h2>Title:{json.data.title}</h2>
        <p>{json.data.text}</p>
    </pre>
{:catch error}
    {error.toString()}
{/await}



</main>
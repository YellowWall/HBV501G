<script>
    import {goto,} from '$app/navigation';
    import {browser} from '$app/environment';
    export let ppid;
    let backendRoute = 'http://localhost:8080/'
    let username = "";
    let title = "";
    let text = "";
    if(browser){
        username = window.sessionStorage.getItem('Username');
    }
    async function submit(){
        const res = await fetch(
            backendRoute,
            {method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                username,
                title,
                text
                })
            }
        )
        return res.json();
    }
    async function trySubmit(){
        if(text.length>0&&username!=""){
            if(ppid=="0"){
                backendRoute += "newPost";
                const json = await submit();
                if(json.success){
                    return true;
                }else{
                    return false;
                }
            }else{
                backendRoute += "replyPost?ppid="+ppid;
                const json = await submit();
                if(json.success){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
</script>
<main>
    <div class="form">
        <p>{username}</p>
        <form action="">
            <input
            bind:value={title}
            type="title"
            placeholder="title">
            <input
            bind:value={text}
            type="text"
            placeholder="text">
            <button
            on:click={trySubmit}>
                Submit post
            </button>
        </form>
</main>
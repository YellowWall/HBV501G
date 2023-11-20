<script>
    import {invalidateAll,goto} from '$app/navigation';
    import {browser} from '$app/environment';
    import { Label, Input, Helper, Button } from 'flowbite-svelte';
    
    export let ppid;
    let backendRoute = 'http://localhost:8080/forum/'
    let username = "";
    let titlefail = false;
    let textfail = false;
    export let id;
    export let text;
    export let title;
    if(id == null){
        id = null;
    }
    if(text == null){
        text = "";
    }
    if(title == null){
        title = "";
    }
    $: textlength = text.length;
    $: titlelength = title.length;
    $: titlelength && check();
    $: textlength && check();
    if(browser){
        username = window.sessionStorage.getItem('Username');
    }
    async function submit(){
        let token;
        if(browser){
            token = window.sessionStorage.getItem('authenticatorTocen');
        }
        console.log(titlefail);
        console.log(textfail);
        console.log("button pressed");
        if(!trySubmit()){
            return;
        };
        let res;
        if(id != null){
            res = await fetch(
                'http://localhost:8080/forum/editPost',
                {
                    method: 'PATCH',
                    headers: {"Content-Type": "application/json",
                            'Authorization': "Bearer "+token},
                    body: JSON.stringify({
                        id,title,text,username,parentPostId:ppid
                    }
                    )
                }
            )
        }else{
            res = await fetch(
                backendRoute,
                {method: 'POST',
                headers: {"Content-Type": "application/json",
                    'Authorization': "Bearer "+token},
                body: JSON.stringify({
                    title,text,username,parentPostId:ppid
                    })
                }
            )
        }

        const json = await res.json();
        console.log(json);
        refresh();
    }
    function check(){
        if(titlelength<50){
            titlefail = false;
        }else titlefail = true;
        if(textlength<500){
            textfail = false;
        }else textfail = true;
    }
    function trySubmit(){
        if(titlefail||textfail){
            return false;
        }
        if(ppid=="0"){
            backendRoute = "http://localhost:8080/forum/newPost";
            return true;
        }else{
            backendRoute = "http://localhost:8080/forum/replyPost?ppid="+ppid;
            return true;
        }
    }


    function refresh(){
        invalidateAll();
        goto(window.location.pathname);
        
    }
</script>
<main>
    <form on:submit|preventDefault={submit}>
    <div>
        <Input label="Username" id="username" value={username} />
    </div>
    <div class="block">
        {#if id != null}
            <b>{title}</b>
        {:else}
        <Label for="title" class="mb-2">Title</Label>
        <Input type="text" color="cream" id="title" bind:value={title} placeholder={title} required/>
        
        {#if titlefail}
            <Helper class="text-sm mt-2">
            title may not be longer than 50 characters
            </Helper>
        {/if}
        {/if}
    </div>
    <div class="block">
        <Input type="textarea" color="cream" id="text" bind:value={text} placeholder="write here" required />
        {#if textfail==true}
            <Helper class="text-sm mt-2">
            text may not be longer than 500 characters
            </Helper>
        {/if}
    </div>
    <Button color="dark" type="submit">Submit Post</Button>
    </form>
    
</main>
